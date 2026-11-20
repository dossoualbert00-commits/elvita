#!/usr/bin/env python3
"""
Générateur d'Affiche Promotionnelle Haute Couture — EL VITA
Style Éditorial Médical Suisse (4:5, 300 DPI PNG + PDF Vectoriel).
Polices statiques Plus Jakarta Sans (400/600/700/800) & DM Serif Display.
QR Code ISO/IEC 18004 (Version 4-L) généré localement en pur Python.
"""

import os
import struct
import zlib

# Dimensions PDF : 480 x 600 pt (Ratio 4:5 -> 2000 x 2500 px à 300 DPI)
W = 480.0
H = 600.0

# Palette Éditoriale Médicale (Zéro effet IA, contraste mat)
CANVAS = (247/255, 249/255, 251/255)      # #F7F9FB Blanc Porcelaine
WHITE = (1.0, 1.0, 1.0)
NAVY = (11/255, 25/255, 44/255)           # #0B192C Bleu Nuit Clinique
NAVY_LINE = (30/255, 58/255, 95/255)      # #1E3A5F
SLATE_DARK = (30/255, 41/255, 59/255)     # #1E293B Titane mat
SLATE = (71/255, 85/255, 105/255)         # #475569 Texte corps
SLATE_MUTED = (100/255, 116/255, 139/255) # #64748B
HAIRLINE = (218/255, 226/255, 236/255)    # #DAE2EC Filet architectural
TEAL = (13/255, 148/255, 136/255)         # #0D9488 Émeraude clinique
TEAL_BG = (236/255, 253/255, 245/255)     # #ECFDF5
BLUE = (2/255, 132/255, 199/255)          # #0284C7 Cobalt médical
SKY = (56/255, 189/255, 248/255)          # #38BDF8 Accent lisible sur fond sombre
SHADOW_1 = (15/255, 23/255, 42/255)


# ==============================================================================
# 1. MOTEUR QR CODE ISO/IEC 18004 (Version 4-L, Mode Octet, 33x33 modules)
# ==============================================================================

def _gf_init():
    exp = [0] * 512
    log = [0] * 256
    x = 1
    for i in range(255):
        exp[i] = x
        log[x] = i
        x <<= 1
        if x & 0x100:
            x ^= 0x11D
    for i in range(255, 512):
        exp[i] = exp[i - 255]
    return exp, log


_GF_EXP, _GF_LOG = _gf_init()


def _gf_mul(a: int, b: int) -> int:
    if a == 0 or b == 0:
        return 0
    return _GF_EXP[_GF_LOG[a] + _GF_LOG[b]]


def _rs_generator(nsym: int):
    g = [1]
    for i in range(nsym):
        # Multiply g by (x - alpha^i), in GF(2^m) addition == subtraction
        factor = [1, _GF_EXP[i]]
        res = [0] * (len(g) + 1)
        for j, gj in enumerate(g):
            res[j] ^= gj
            res[j + 1] ^= _gf_mul(gj, factor[1])
        g = res
    return g


def _rs_encode(data: list[int], nsym: int) -> list[int]:
    gen = _rs_generator(nsym)
    msg = list(data) + [0] * nsym
    for i in range(len(data)):
        coef = msg[i]
        if coef != 0:
            for j in range(1, len(gen)):
                msg[i + j] ^= _gf_mul(gen[j], coef)
    return msg[len(data):]


def _bch_format(ec_bits: int, mask: int) -> int:
    """Calcule les 15 bits de format info QR (EC L=01, mask 0..7)."""
    data = (ec_bits << 3) | mask
    rem = data << 10
    gen = 0b10100110111  # x^10 + x^8 + x^5 + x^4 + x^2 + x + 1
    for i in range(14, 9, -1):
        if rem & (1 << i):
            rem ^= (gen << (i - 10))
    raw = (data << 10) | rem
    return raw ^ 0b101010000010010


def encode_qr_v4_l(url: str) -> list[list[int]]:
    """Encode une URL (<= 78 octets) en matrice QR 33x33 (Version 4-L)."""
    payload = url.encode('utf-8')
    assert len(payload) <= 78, f"Payload trop long pour V4-L: {len(payload)}"

    # Flux binaire : Mode Byte (0100) + Longueur (8 bits) + Octets + Terminateur (0000)
    bits = []
    def push_bits(val: int, count: int):
        for k in range(count - 1, -1, -1):
            bits.append((val >> k) & 1)

    push_bits(0b0100, 4)
    push_bits(len(payload), 8)
    for b in payload:
        push_bits(b, 8)
    # Terminateur (max 4 bits) jusqu'à 640 bits
    for _ in range(min(4, 640 - len(bits))):
        bits.append(0)
    while len(bits) % 8 != 0:
        bits.append(0)

    codewords = []
    for i in range(0, len(bits), 8):
        val = 0
        for b in bits[i:i+8]:
            val = (val << 1) | b
        codewords.append(val)

    pad_bytes = [0xEC, 0x11]
    idx = 0
    while len(codewords) < 80:
        codewords.append(pad_bytes[idx % 2])
        idx += 1

    ec = _rs_encode(codewords, 20)
    all_cw = codewords + ec  # 100 codewords -> 800 bits
    stream = []
    for cw in all_cw:
        for k in range(7, -1, -1):
            stream.append((cw >> k) & 1)
    # 7 remainder bits pour Version 4
    stream.extend([0] * 7)

    size = 33
    mat = [[None] * size for _ in range(size)]
    func = [[False] * size for _ in range(size)]

    def set_func(r: int, c: int, val: int):
        mat[r][c] = val
        func[r][c] = True

    def draw_finder(r0: int, c0: int):
        for dr in range(-1, 8):
            for dc in range(-1, 8):
                r, c = r0 + dr, c0 + dc
                if 0 <= r < size and 0 <= c < size:
                    if dr in (-1, 7) or dc in (-1, 7):
                        set_func(r, c, 0)
                    elif dr in (0, 6) or dc in (0, 6) or (2 <= dr <= 4 and 2 <= dc <= 4):
                        set_func(r, c, 1)
                    else:
                        set_func(r, c, 0)

    draw_finder(0, 0)
    draw_finder(0, size - 7)
    draw_finder(size - 7, 0)

    # Alignment pattern Version 4 à (26, 26)
    for dr in range(-2, 3):
        for dc in range(-2, 3):
            v = 1 if (abs(dr) == 2 or abs(dc) == 2 or (dr == 0 and dc == 0)) else 0
            set_func(26 + dr, 26 + dc, v)

    # Timing patterns (ligne 6 & colonne 6)
    for i in range(8, size - 8):
        val = 1 if (i % 2 == 0) else 0
        if not func[6][i]:
            set_func(6, i, val)
        if not func[i][6]:
            set_func(i, 6, val)

    # Dark module à (4*4 + 9, 8) = (25, 8)
    set_func(25, 8, 1)

    # Réserver format info (rempli après masquage)
    for i in range(9):
        if i != 6:
            func[8][i] = True
            func[i][8] = True
    for i in range(8):
        func[8][size - 1 - i] = True
        if i < 7:
            func[size - 1 - i][8] = True

    # Placement des données en zig-zag
    bit_idx = 0
    col = size - 1
    upward = True
    while col > 0:
        if col == 6:
            col -= 1
        rows = range(size - 1, -1, -1) if upward else range(size)
        for r in rows:
            for dc in (0, -1):
                c = col + dc
                if not func[r][c]:
                    mat[r][c] = stream[bit_idx]
                    bit_idx += 1
        upward = not upward
        col -= 2
    assert bit_idx == len(stream), f"Bits placés: {bit_idx} / {len(stream)}"

    def apply_mask(mask_id: int):
        grid = [[mat[r][c] for c in range(size)] for r in range(size)]
        for r in range(size):
            for c in range(size):
                if not func[r][c]:
                    cond = False
                    if mask_id == 0: cond = (r + c) % 2 == 0
                    elif mask_id == 1: cond = r % 2 == 0
                    elif mask_id == 2: cond = c % 3 == 0
                    elif mask_id == 3: cond = (r + c) % 3 == 0
                    elif mask_id == 4: cond = ((r // 2) + (c // 3)) % 2 == 0
                    elif mask_id == 5: cond = ((r * c) % 2 + (r * c) % 3) == 0
                    elif mask_id == 6: cond = (((r * c) % 2 + (r * c) % 3) % 2) == 0
                    elif mask_id == 7: cond = (((r + c) % 2 + (r * c) % 3) % 2) == 0
                    if cond:
                        grid[r][c] ^= 1

        fmt = _bch_format(0b01, mask_id)  # L = 01
        fmt_bits = [(fmt >> i) & 1 for i in range(14, -1, -1)]
        # Copy 1: top-left (MSB=0 at (8,0) .. LSB=14 at (0,8))
        coords_h = [(8, 0), (8, 1), (8, 2), (8, 3), (8, 4), (8, 5), (8, 7), (8, 8)]
        coords_v = [(7, 8), (5, 8), (4, 8), (3, 8), (2, 8), (1, 8), (0, 8)]
        for idx_f, (rr, cc) in enumerate(coords_h + coords_v):
            grid[rr][cc] = fmt_bits[idx_f]
        # Copy 2: bottom-left (bits 0..6) & top-right (bits 7..14)
        for i in range(7):
            grid[size - 1 - i][8] = fmt_bits[i]
        for i in range(8):
            grid[8][size - 8 + i] = fmt_bits[7 + i]
        return grid

    def penalty(grid):
        score = 0
        # Règle 1 : lignes & colonnes
        for is_row in (True, False):
            for i in range(size):
                run_col = -1
                run_len = 0
                for j in range(size):
                    v = grid[i][j] if is_row else grid[j][i]
                    if v == run_col:
                        run_len += 1
                    else:
                        if run_len >= 5:
                            score += 3 + (run_len - 5)
                        run_col = v
                        run_len = 1
                if run_len >= 5:
                    score += 3 + (run_len - 5)
        # Règle 2 : blocs 2x2
        for r in range(size - 1):
            for c in range(size - 1):
                v = grid[r][c]
                if v == grid[r][c+1] == grid[r+1][c] == grid[r+1][c+1]:
                    score += 3
        # Règle 3 : motif finder-like 1011101
        pat1 = [1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0]
        pat2 = [0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1]
        for i in range(size):
            row = grid[i]
            col_arr = [grid[r][i] for r in range(size)]
            for k in range(size - 10):
                if row[k:k+11] in (pat1, pat2):
                    score += 40
                if col_arr[k:k+11] in (pat1, pat2):
                    score += 40
        # Règle 4 : ratio noir/blanc
        dark = sum(sum(r) for r in grid)
        pct = (dark * 100.0) / (size * size)
        prev5 = int(abs(pct - 50) // 5)
        score += prev5 * 10
        return score

    best_grid = None
    best_score = 10**9
    for m in range(8):
        g = apply_mask(m)
        sc = penalty(g)
        if sc < best_score:
            best_score = sc
            best_grid = g
    return best_grid


# ==============================================================================
# 2. PARSING POLICES TRUETYPE & MOTEUR PDF VECTORIEL
# ==============================================================================

def parse_ttf_winansi_metrics(ttf_path: str):
    data = open(ttf_path, 'rb').read()
    num_tables = struct.unpack('>H', data[4:6])[0]
    tables = {}
    for i in range(num_tables):
        off = 12 + i * 16
        tag = data[off:off+4].decode('latin1')
        t_off, t_len = struct.unpack('>II', data[off+8:off+16])
        tables[tag] = (t_off, t_len)

    h_off = tables['head'][0]
    units_per_em = struct.unpack('>H', data[h_off+18:h_off+20])[0]
    xmin, ymin, xmax, ymax = struct.unpack('>hhhh', data[h_off+36:h_off+44])
    scale = 1000.0 / units_per_em

    hh_off = tables['hhea'][0]
    ascent, descent = struct.unpack('>hh', data[hh_off+4:hh_off+8])
    num_hmetrics = struct.unpack('>H', data[hh_off+34:hh_off+36])[0]

    hm_off = tables['hmtx'][0]
    adv_widths = [struct.unpack('>H', data[hm_off + i*4 : hm_off + i*4 + 2])[0] for i in range(num_hmetrics)]

    cm_off = tables['cmap'][0]
    num_sub = struct.unpack('>H', data[cm_off+2:cm_off+4])[0]
    sub_off = None
    for i in range(num_sub):
        pid, eid, rel_off = struct.unpack('>HHI', data[cm_off+4+i*8 : cm_off+12+i*8])
        if (pid == 3 and eid == 1) or pid == 0:
            sub_off = cm_off + rel_off
            if pid == 3:
                break
    seg_count = struct.unpack('>H', data[sub_off+6:sub_off+8])[0] // 2
    end_codes = struct.unpack(f'>{seg_count}H', data[sub_off+14 : sub_off+14+2*seg_count])
    start_codes_off = sub_off + 16 + 2*seg_count
    start_codes = struct.unpack(f'>{seg_count}H', data[start_codes_off : start_codes_off+2*seg_count])
    id_delta_off = start_codes_off + 2*seg_count
    id_deltas = struct.unpack(f'>{seg_count}h', data[id_delta_off : id_delta_off+2*seg_count])
    id_range_off_base = id_delta_off + 2*seg_count
    id_range_offs = struct.unpack(f'>{seg_count}H', data[id_range_off_base : id_range_off_base+2*seg_count])

    def glyph_id(cp):
        for i in range(seg_count):
            if start_codes[i] <= cp <= end_codes[i]:
                if id_range_offs[i] == 0:
                    return (cp + id_deltas[i]) & 0xFFFF
                else:
                    addr = id_range_off_base + 2*i + id_range_offs[i] + 2*(cp - start_codes[i])
                    gid = struct.unpack('>H', data[addr:addr+2])[0]
                    return (gid + id_deltas[i]) & 0xFFFF if gid != 0 else 0
        return 0

    widths = []
    for b in range(32, 256):
        try:
            ch = bytes([b]).decode('cp1252')
            gid = glyph_id(ord(ch))
        except UnicodeDecodeError:
            gid = 0
        aw = adv_widths[gid] if gid < len(adv_widths) else adv_widths[-1]
        widths.append(round(aw * scale))

    return {
        'raw': data,
        'compressed': zlib.compress(data),
        'bbox': [round(xmin*scale), round(ymin*scale), round(xmax*scale), round(ymax*scale)],
        'ascent': round(ascent * scale),
        'descent': round(descent * scale),
        'widths': widths,
    }


FONT_METRICS = {
    'F1': parse_ttf_winansi_metrics('/tmp/poster_fonts/pjs_400.ttf'),
    'F2': parse_ttf_winansi_metrics('/tmp/poster_fonts/pjs_600.ttf'),
    'F3': parse_ttf_winansi_metrics('/tmp/poster_fonts/pjs_700.ttf'),
    'F4': parse_ttf_winansi_metrics('/tmp/poster_fonts/pjs_800.ttf'),
    'F5': parse_ttf_winansi_metrics('/tmp/poster_fonts/dm_serif_display.ttf'),
    'F6': parse_ttf_winansi_metrics('/tmp/poster_fonts/dmsd_italic.ttf'),
}


def pdf_escape(text: str) -> bytes:
    text = text.replace('\u2192', '->').replace('\u202f', ' ').replace('\u00a0', ' ')
    raw = text.encode('cp1252', errors='replace')
    out = bytearray()
    for b in raw:
        if b == ord('('): out.extend(b'\\(')
        elif b == ord(')'): out.extend(b'\\)')
        elif b == ord('\\'): out.extend(b'\\\\')
        else: out.append(b)
    return bytes(out)


def text_width(text: str, font_size: float, font='F1', tracking=0.0) -> float:
    text = text.replace('\u2192', '->').replace('\u202f', ' ').replace('\u00a0', ' ')
    raw = text.encode('cp1252', errors='replace')
    widths = FONT_METRICS[font]['widths']
    total = 0
    for b in raw:
        total += widths[b - 32] if 32 <= b <= 255 else 600
    w = (total / 1000.0) * font_size
    if len(raw) > 1 and tracking != 0.0:
        w += (len(raw) - 1) * tracking
    return w


class PosterCanvas:
    def __init__(self, width=W, height=H):
        self.w = width
        self.h = height
        self.ops = []
        self.links = []

    def rgb_fill(self, c):
        self.ops.append(f"{c[0]:.4f} {c[1]:.4f} {c[2]:.4f} rg")

    def rgb_stroke(self, c):
        self.ops.append(f"{c[0]:.4f} {c[1]:.4f} {c[2]:.4f} RG")

    def line(self, x1, y1, x2, y2, color, lw=0.6):
        self.ops.append(f"q {lw:.2f} w")
        self.rgb_stroke(color)
        self.ops.append(f"{x1:.2f} {y1:.2f} m {x2:.2f} {y2:.2f} l S Q")

    def rect(self, x, y, w, h, fill=None, stroke=None, lw=0.6):
        self.ops.append("q")
        if fill is not None: self.rgb_fill(fill)
        if stroke is not None:
            self.rgb_stroke(stroke)
            self.ops.append(f"{lw:.2f} w")
        self.ops.append(f"{x:.2f} {y:.2f} {w:.2f} {h:.2f} re")
        if fill is not None and stroke is not None: self.ops.append("B")
        elif fill is not None: self.ops.append("f")
        elif stroke is not None: self.ops.append("S")
        self.ops.append("Q")

    def _rrect_path(self, x, y, w, h, r):
        k = 0.5522847498
        r = min(r, w / 2, h / 2)
        kr = r * k
        return "\n".join([
            f"{x+r:.2f} {y:.2f} m",
            f"{x+w-r:.2f} {y:.2f} l",
            f"{x+w-r+kr:.2f} {y:.2f} {x+w:.2f} {y+r-kr:.2f} {x+w:.2f} {y+r:.2f} c",
            f"{x+w:.2f} {y+h-r:.2f} l",
            f"{x+w:.2f} {y+h-r+kr:.2f} {x+w-r+kr:.2f} {y+h:.2f} {x+w-r:.2f} {y+h:.2f} c",
            f"{x+r:.2f} {y+h:.2f} l",
            f"{x+r-kr:.2f} {y+h:.2f} {x:.2f} {y+h-r+kr:.2f} {x:.2f} {y+h-r:.2f} c",
            f"{x:.2f} {y+r:.2f} l",
            f"{x:.2f} {y+r-kr:.2f} {x+r-kr:.2f} {y:.2f} {x+r:.2f} {y:.2f} c",
            "h"
        ])

    def rrect(self, x, y, w, h, r, fill=None, stroke=None, lw=0.75):
        self.ops.append("q")
        if fill is not None: self.rgb_fill(fill)
        if stroke is not None:
            self.rgb_stroke(stroke)
            self.ops.append(f"{lw:.2f} w")
        self.ops.append(self._rrect_path(x, y, w, h, r))
        if fill is not None and stroke is not None: self.ops.append("B")
        elif fill is not None: self.ops.append("f")
        elif stroke is not None: self.ops.append("S")
        self.ops.append("Q")

    def image_rrect(self, img_name, x, y, w, h, r=0.0):
        self.ops.append("q")
        if r > 0:
            self.ops.append(self._rrect_path(x, y, w, h, r))
            self.ops.append("W n")
        self.ops.append(f"{w:.2f} 0 0 {h:.2f} {x:.2f} {y:.2f} cm /{img_name} Do Q")

    def circle(self, cx, cy, r, fill=None, stroke=None, lw=0.8):
        k = 0.5522847498 * r
        self.ops.append("q")
        if fill is not None: self.rgb_fill(fill)
        if stroke is not None:
            self.rgb_stroke(stroke)
            self.ops.append(f"{lw:.2f} w")
        self.ops.append(f"{cx+r:.2f} {cy:.2f} m")
        self.ops.append(f"{cx+r:.2f} {cy+k:.2f} {cx+k:.2f} {cy+r:.2f} {cx:.2f} {cy+r:.2f} c")
        self.ops.append(f"{cx-k:.2f} {cy+r:.2f} {cx-r:.2f} {cy+k:.2f} {cx-r:.2f} {cy:.2f} c")
        self.ops.append(f"{cx-r:.2f} {cy-k:.2f} {cx-k:.2f} {cy-r:.2f} {cx:.2f} {cy-r:.2f} c")
        self.ops.append(f"{cx+k:.2f} {cy-r:.2f} {cx+r:.2f} {cy-k:.2f} {cx+r:.2f} {cy:.2f} c h")
        if fill is not None and stroke is not None: self.ops.append("B")
        elif fill is not None: self.ops.append("f")
        elif stroke is not None: self.ops.append("S")
        self.ops.append("Q")

    def text(self, x, y, txt, size=10.0, font='F1', color=NAVY, align='left', tracking=0.0):
        tw = text_width(txt, size, font, tracking)
        tx = x
        if align == 'center': tx = x - tw / 2.0
        elif align == 'right': tx = x - tw
        self.ops.append("q BT")
        self.rgb_fill(color)
        self.ops.append(f"/{font} {size:.2f} Tf")
        if tracking != 0.0:
            self.ops.append(f"{tracking:.3f} Tc")
        self.ops.append(f"1 0 0 1 {tx:.2f} {y:.2f} Tm")
        self.ops.append(b"(" + pdf_escape(txt) + b") Tj ET Q\n")

    def link(self, x, y, w, h, uri):
        self.links.append((x, y, x + w, y + h, uri))

    def build_stream(self) -> bytes:
        chunks = []
        for item in self.ops:
            if isinstance(item, bytes):
                chunks.append(item)
            else:
                chunks.append(item.encode('ascii'))
                chunks.append(b"\n")
        return b"".join(chunks)


def read_jpeg_info(path: str):
    data = open(path, 'rb').read()
    i = 2
    while i < len(data):
        if data[i] != 0xFF:
            i += 1
            continue
        marker = data[i+1]
        if marker in (0xC0, 0xC1, 0xC2):
            h, w, comp = struct.unpack('>HHB', data[i+5:i+10])
            return data, w, h, comp
        elif marker in (0xD8, 0xD9, 0x01) or (0xD0 <= marker <= 0xD7):
            i += 2
        else:
            seg_len = struct.unpack('>H', data[i+2:i+4])[0]
            i += 2 + seg_len
    raise ValueError(f"Dimensions JPEG introuvables: {path}")


def write_pdf(canvas: PosterCanvas, images: dict, out_path: str):
    objects = []

    def add_obj(body: bytes) -> int:
        objects.append(body)
        return len(objects)

    # Fonts F1..F6
    font_meta = [
        ('F1', 'PlusJakartaSans-Regular'),
        ('F2', 'PlusJakartaSans-SemiBold'),
        ('F3', 'PlusJakartaSans-Bold'),
        ('F4', 'PlusJakartaSans-ExtraBold'),
        ('F5', 'DMSerifDisplay-Regular'),
        ('F6', 'DMSerifDisplay-Italic'),
    ]
    font_ids = {}
    for key, ps_name in font_meta:
        fm = FONT_METRICS[key]
        raw_len = len(fm['raw'])
        comp = fm['compressed']
        stream_id = add_obj(
            f"<< /Length {len(comp)} /Length1 {raw_len} /Filter /FlateDecode >>\nstream\n".encode('ascii')
            + comp + b"\nendstream"
        )
        bb = fm['bbox']
        desc_id = add_obj(
            f"<< /Type /FontDescriptor /FontName /{ps_name} /Flags 32 "
            f"/FontBBox [{bb[0]} {bb[1]} {bb[2]} {bb[3]}] /ItalicAngle { -12 if key=='F6' else 0 } "
            f"/Ascent {fm['ascent']} /Descent {fm['descent']} /CapHeight {fm['ascent']} /StemV 80 "
            f"/FontFile2 {stream_id} 0 R >>".encode('ascii')
        )
        widths_str = " ".join(str(w) for w in fm['widths'])
        font_ids[key] = add_obj(
            f"<< /Type /Font /Subtype /TrueType /BaseFont /{ps_name} "
            f"/Encoding /WinAnsiEncoding /FirstChar 32 /LastChar 255 "
            f"/Widths [{widths_str}] /FontDescriptor {desc_id} 0 R >>".encode('ascii')
        )

    img_ids = {}
    for key, path in images.items():
        jdata, iw, ih, comp = read_jpeg_info(path)
        cs = "/DeviceRGB" if comp == 3 else "/DeviceGray"
        img_ids[key] = add_obj(
            f"<< /Type /XObject /Subtype /Image /Width {iw} /Height {ih} "
            f"/ColorSpace {cs} /BitsPerComponent 8 /Filter /DCTDecode /Length {len(jdata)} >>\nstream\n".encode('ascii')
            + jdata + b"\nendstream"
        )

    xobj_str = " ".join(f"/{k} {v} 0 R" for k, v in img_ids.items())
    font_str = " ".join(f"/{k} {v} 0 R" for k, v in font_ids.items())
    res_dict = f"<< /Font << {font_str} >> /XObject << {xobj_str} >> >>"

    raw_stream = canvas.build_stream()
    comp_stream = zlib.compress(raw_stream)
    content_id = add_obj(
        f"<< /Length {len(comp_stream)} /Filter /FlateDecode >>\nstream\n".encode('ascii')
        + comp_stream + b"\nendstream"
    )

    annot_ids = []
    for (x1, y1, x2, y2, uri) in canvas.links:
        annot_ids.append(add_obj(
            f"<< /Type /Annot /Subtype /Link /Rect [{x1:.2f} {y1:.2f} {x2:.2f} {y2:.2f}] "
            f"/Border [0 0 0] /A << /S /URI /URI ({uri}) >> >>".encode('ascii')
        ))
    annots_str = ("/Annots [" + " ".join(f"{aid} 0 R" for aid in annot_ids) + "]") if annot_ids else ""

    pages_placeholder = len(objects) + 2
    page_id = add_obj(
        f"<< /Type /Page /Parent {pages_placeholder} 0 R /MediaBox [0 0 {W:.2f} {H:.2f}] "
        f"/Resources {res_dict} /Contents {content_id} 0 R {annots_str} >>".encode('ascii')
    )
    pages_id = add_obj(f"<< /Type /Pages /Count 1 /Kids [{page_id} 0 R] >>".encode('ascii'))
    catalog_id = add_obj(f"<< /Type /Catalog /Pages {pages_id} 0 R >>".encode('ascii'))

    out = bytearray(b"%PDF-1.4\n%\xe2\xe3\xcf\xd3\n")
    offsets = [0]
    for idx, body in enumerate(objects, start=1):
        offsets.append(len(out))
        out.extend(f"{idx} 0 obj\n".encode('ascii'))
        out.extend(body)
        out.extend(b"\nendobj\n")

    xref_off = len(out)
    out.extend(f"xref\n0 {len(objects)+1}\n0000000000 65535 f \n".encode('ascii'))
    for off in offsets[1:]:
        out.extend(f"{off:010d} 00000 n \n".encode('ascii'))
    out.extend(f"trailer\n<< /Size {len(objects)+1} /Root {catalog_id} 0 R >>\nstartxref\n{xref_off}\n%%EOF\n".encode('ascii'))

    with open(out_path, 'wb') as f:
        f.write(out)


def build_poster(out_pdf: str):
    c = PosterCanvas()
    url_ios = "https://dossoualbert00-commits.github.io/elvita/"
    qr = encode_qr_v4_l(url_ios)

    # 1. Fond porcelaine mat
    c.rect(0, 0, W, H, fill=CANVAS)

    # Liseré supérieur discret (3 pt bleu nuit + 1.5 pt émeraude)
    c.rect(0, H - 3.5, W, 3.5, fill=NAVY)
    c.rect(0, H - 5.0, W * 0.36, 1.5, fill=TEAL)

    # ==========================================================================
    # ZONE 1 : HEADER ÉDITORIAL (y = 532 .. 580)
    # ==========================================================================
    M = 34.0  # Marge latérale stricte de la grille suisse
    c.image_rrect('logo', M, 540, 32, 32, r=7.5)
    c.text(M + 41, 557, "EL VITA", size=14.5, font='F4', color=NAVY, tracking=0.8)
    c.text(M + 41, 544.5, "COMPAGNON CLINIQUE & PÉDAGOGIQUE", size=6.8, font='F2', color=SLATE_MUTED, tracking=0.55)

    # Badge sobre à droite (sans néon)
    pill_txt = "100 % GRATUIT  ·  HORS-LIGNE"
    pw = text_width(pill_txt, 6.8, 'F3', 0.35) + 18
    c.rrect(W - M - pw, 546.5, pw, 19, 9.5, fill=TEAL_BG, stroke=TEAL, lw=0.65)
    c.text(W - M - pw / 2, 553.6, pill_txt, size=6.8, font='F3', color=TEAL, align='center', tracking=0.35)

    c.line(M, 526, W - M, 526, HAIRLINE, lw=0.7)

    # ==========================================================================
    # ZONE 2 : HERO ASYMÉTRIQUE (y = 202 .. 514)
    # ==========================================================================
    # Colonne Gauche (x = 34 .. 274)
    c.rect(M, 496, 18, 2.0, fill=TEAL)
    c.text(M + 24, 494.5, "MÉDECINE  ·  EXTERNAT  ·  GARDES", size=7.2, font='F3', color=TEAL, tracking=0.7)

    # Titre éditorial haute couture (DM Serif Display)
    c.text(M, 456, "L'outil clinique", size=28.5, font='F5', color=NAVY)
    c.text(M, 425, "au lit du malade.", size=28.5, font='F5', color=NAVY)

    # Slogan en italique éditorial
    c.text(M, 401, "Apprendre. Comprendre. Pratiquer.", size=11.5, font='F6', color=BLUE)

    # Phrase d'accroche sobre (2 lignes max)
    c.text(M, 379, "L'application médicale de poche conçue pour les", size=8.6, font='F1', color=SLATE)
    c.text(M, 366.5, "étudiants, le tour de salle et les gardes d'urgences.", size=8.6, font='F1', color=SLATE)

    c.line(M, 353, 256, 353, HAIRLINE, lw=0.6)

    # 4 Piliers épurés (numérotation éditoriale, zéro boîtes surchargées)
    pillars = [
        ("01", "Dossiers & Interrogatoire", "Observations locales & méthode en 14 points"),
        ("02", "Constantes & Alertes", "Calcul PAM, IMC et courbes évolutives"),
        ("03", "Biologie & Glasgow", "NFS 6 volets, Rein (KDIGO), Foie & Hémostase"),
        ("04", "29 Fiches & 90 QCM", "Sémiologie complète avec corrections"),
    ]
    py = 330.0
    for num, title, sub in pillars:
        # Numéro index discret
        c.text(M, py, num, size=8.5, font='F4', color=TEAL, tracking=0.3)
        c.line(M + 17, py - 2, M + 17, py + 8, HAIRLINE, lw=0.8)
        c.text(M + 25, py + 1.5, title, size=10.2, font='F3', color=NAVY)
        c.text(M + 25, py - 10.5, sub, size=7.8, font='F1', color=SLATE)
        py -= 34.5

    # Colonne Droite : Smartphone Mat Titane avec vraie UI (x = 296 .. 446, w = 148, h = 294)
    px, py_box, pw_box, ph_box = 296.0, 224.0, 148.0, 294.0
    # Ombre portée multi-couches très douce et réaliste (non-IA)
    for step, (dx, dy, op) in enumerate([(0, -5, 0.94), (0, -3, 0.90), (0, -1.5, 0.85)]):
        sh_col = (
            CANVAS[0] * op + SHADOW_1[0] * (1 - op),
            CANVAS[1] * op + SHADOW_1[1] * (1 - op),
            CANVAS[2] * op + SHADOW_1[2] * (1 - op),
        )
        c.rrect(px - (3-step)*1.2, py_box + dy - (2-step), pw_box + (3-step)*2.4, ph_box + 2, 16, fill=sh_col)

    # Châssis titane mat
    c.rrect(px, py_box, pw_box, ph_box, 14.5, fill=SLATE_DARK, stroke=(71/255, 85/255, 105/255), lw=0.9)
    # Écran interne bord-à-bord (arrondi doux 9 pt pour préserver Accueil & Sémiologie)
    pad = 4.2
    sx, sy, sw, sh = px + pad, py_box + pad, pw_box - 2*pad, ph_box - 2*pad
    c.rrect(sx, sy, sw, sh, 9.5, fill=(7/255, 16/255, 29/255))
    c.image_rrect('screen', sx, sy, sw, sh - 2.0, r=9.0)
    # Fin ilot haut minimaliste
    c.rrect(px + pw_box/2 - 14, py_box + ph_box - 10.5, 28, 4.5, 2.2, fill=(10/255, 15/255, 25/255))

    # Libellé sobre centré sous le téléphone
    lbl = "Android (.APK)  &  iPhone (Safari)"
    lw_txt = text_width(lbl, 7.1, 'F3')
    start_x = px + (pw_box - (lw_txt + 10)) / 2.0
    c.circle(start_x + 2.5, 204.5, 2.4, fill=TEAL)
    c.text(start_x + 9.0, 202.0, lbl, size=7.1, font='F3', color=NAVY)

    # ==========================================================================
    # ZONE 3 : BANDEAU DE CONFIANCE ÉDITORIAL (y = 144 .. 188)
    # ==========================================================================
    c.rect(0, 144, W, 44, fill=WHITE)
    c.line(0, 188, W, 188, HAIRLINE, lw=0.7)
    c.line(0, 144, W, 144, HAIRLINE, lw=0.7)

    trust_items = [
        ("Secret médical strict", "Données 100 % locales"),
        ("Sans connexion internet", "Fiable en salle & urgences"),
        ("Zéro publicité", "Accès libre et volontaire"),
    ]
    col_w = (W - 2 * M) / 3.0
    for idx, (t_head, t_sub) in enumerate(trust_items):
        cx = M + idx * col_w
        if idx > 0:
            c.line(cx - 8, 153, cx - 8, 179, HAIRLINE, lw=0.6)
        c.circle(cx + 4, 169.5, 2.5, fill=TEAL if idx != 1 else BLUE)
        c.text(cx + 12, 167.2, t_head, size=8.2, font='F3', color=NAVY)
        c.text(cx + 12, 156.5, t_sub, size=7.1, font='F1', color=SLATE_MUTED)

    # ==========================================================================
    # ZONE 4 : FOOTER ARCHITECTURAL D'ACTION & SIGNATURE (y = 0 .. 144)
    # ==========================================================================
    c.rect(0, 0, W, 144, fill=NAVY)
    c.rect(0, 142.5, W, 1.5, fill=TEAL)

    # QR Code Card à droite (x = 362 .. 446, w = 84)
    qbox_x, qbox_y, qbox_w, qbox_h = W - M - 84, 24, 84, 98
    c.rrect(qbox_x, qbox_y, qbox_w, qbox_h, 8, fill=WHITE)
    # Dessiner le QR 33x33 avec marge calme (quiet zone)
    qr_size = len(qr)
    qr_draw = 66.0
    mod = qr_draw / qr_size
    qx0 = qbox_x + (qbox_w - qr_draw) / 2.0
    qy_top = qbox_y + qbox_h - 9.0
    for r in range(qr_size):
        for col_q in range(qr_size):
            if qr[r][col_q] == 1:
                c.rect(qx0 + col_q * mod, qy_top - (r + 1) * mod, mod + 0.04, mod + 0.04, fill=NAVY)
    c.text(qbox_x + qbox_w / 2.0, qbox_y + 8.5, "SCANNER · IPHONE", size=6.2, font='F4', color=NAVY, align='center', tracking=0.4)
    c.link(qbox_x, qbox_y, qbox_w, qbox_h, url_ios)

    # Instructions d'accès à gauche (x = 34 .. 346)
    c.text(M, 121, "TESTER ET PARTAGER EL VITA", size=7.2, font='F3', color=SKY, tracking=0.7)
    c.text(M, 102, "Android :", size=8.4, font='F3', color=WHITE)
    c.text(M + 45, 102, "Installer directement le fichier  EL VITA.apk", size=8.2, font='F1', color=(215/255, 224/255, 234/255))

    c.text(M, 86, "iPhone :", size=8.4, font='F3', color=WHITE)
    c.text(M + 45, 86, "Ouvrir dans Safari :  dossoualbert00-commits.github.io/elvita", size=8.2, font='F2', color=SKY)
    c.link(M + 45, 82, 240, 12, url_ios)
    c.text(M + 45, 73.5, "puis  Partager  ->  Sur l'écran d'accueil", size=7.6, font='F1', color=(175/255, 190/255, 208/255))

    # Filet de séparation discret
    c.line(M, 60, qbox_x - 18, 60, NAVY_LINE, lw=0.7)

    # Signature Fondateur avec portrait proprement cadré
    c.circle(M + 17, 35.5, 18.0, fill=NAVY_LINE, stroke=SKY, lw=0.9)
    c.image_rrect('founder', M + 1, 19.5, 32, 32, r=16.0)

    c.text(M + 44, 40.5, "Initié par Albert DOSSOU", size=8.8, font='F3', color=WHITE)
    c.text(M + 44, 29.0, "Fondateur & Propriétaire  ·  dossoualbert00@gmail.com", size=7.2, font='F1', color=(185/255, 200/255, 216/255))
    c.link(M + 135, 26, 120, 11, "mailto:dossoualbert00@gmail.com")
    c.text(M + 44, 18.0, "Utilisation libre et volontaire — Vos retours améliorent les prochaines versions.", size=6.7, font='F1', color=TEAL if False else (130/255, 152/255, 178/255))

    images = {
        'logo': '/tmp/poster_assets/logo_light.jpg',
        'screen': '/tmp/poster_assets/screen_home.jpg',
        'founder': '/tmp/poster_assets/founder_head.jpg',
    }
    write_pdf(c, images, out_pdf)


if __name__ == '__main__':
    os.makedirs('docs', exist_ok=True)
    out_pdf = 'docs/EL_VITA_Affiche_Officielle.pdf'
    build_poster(out_pdf)
    print("OK:", out_pdf, os.path.getsize(out_pdf))
