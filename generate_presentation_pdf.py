#!/usr/bin/env python3
"""
Générateur du Dossier Officiel de Présentation EL VITA (PDF Multi-pages Haute Qualité)
Avec polices TrueType embarquées (DejaVuSans), calculs exacts de largeur de texte,
photo officielle du fondateur Albert DOSSOU et captures d'écran réelles de l'application.
"""

import os
import struct
import zlib

PAGE_W = 595.28
PAGE_H = 841.89

# Palette institutionnelle EL VITA (RGB 0..1)
NAVY = (11/255, 25/255, 44/255)         # #0B192C
NAVY_MID = (19/255, 42/255, 74/255)     # #132A4A
BLUE = (2/255, 132/255, 199/255)        # #0284C7
BLUE_LIGHT = (56/255, 189/255, 248/255) # #38BDF8
BLUE_PALE = (239/255, 246/255, 255/255) # #EFF6FF
TEAL = (13/255, 148/255, 136/255)       # #0D9488
TEAL_PALE = (240/255, 253/255, 250/255) # #F0FDFA
GREEN = (16/255, 185/255, 129/255)      # #10B981
GREEN_DARK = (6/255, 95/255, 70/255)    # #065F46
GREEN_PALE = (236/255, 253/255, 245/255)# #ECFDF5
ORANGE = (217/255, 119/255, 6/255)      # #D97706
ORANGE_PALE = (255/255, 251/255, 235/255)# #FFFBEB
RED = (220/255, 38/255, 38/255)         # #DC2626
RED_PALE = (254/255, 242/255, 242/255)  # #FEF2F2
TEXT_DARK = (15/255, 23/255, 42/255)    # #0F172A
TEXT_BODY = (51/255, 65/255, 85/255)    # #334155
TEXT_MUTED = (100/255, 116/255, 139/255)# #64748B
BORDER = (203/255, 213/255, 225/255)    # #CBD5E1
BG_LIGHT = (248/255, 250/255, 252/255)  # #F8FAFC
WHITE = (1.0, 1.0, 1.0)


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
    'F1': parse_ttf_winansi_metrics('/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf'),
    'F2': parse_ttf_winansi_metrics('/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf'),
    'F3': parse_ttf_winansi_metrics('/usr/share/fonts/truetype/dejavu/DejaVuSerif.ttf'),
}


def sanitize_cp1252(text: str) -> str:
    replacements = {
        '\u2264': '<=',
        '\u2265': '>=',
        '\u2192': '->',
        '\u2713': 'OK',
        '\u2714': 'OK',
        '\u2717': 'X',
        '\u202f': ' ',
        '\u00a0': ' ',
        '\u2009': ' ',
    }
    for k, v in replacements.items():
        text = text.replace(k, v)
    return text


def pdf_escape(text: str) -> bytes:
    text = sanitize_cp1252(text)
    raw = text.encode('cp1252', errors='replace')
    out = bytearray()
    for b in raw:
        if b == ord('('):
            out.extend(b'\\(')
        elif b == ord(')'):
            out.extend(b'\\)')
        elif b == ord('\\'):
            out.extend(b'\\\\')
        else:
            out.append(b)
    return bytes(out)


def text_width(text: str, font_size: float, font='F1') -> float:
    text = sanitize_cp1252(text)
    raw = text.encode('cp1252', errors='replace')
    widths = FONT_METRICS[font]['widths']
    total = 0
    for b in raw:
        if 32 <= b <= 255:
            total += widths[b - 32]
        else:
            total += 600
    return (total / 1000.0) * font_size


def wrap_text(text: str, max_width: float, font_size: float, font='F1') -> list:
    paragraphs = text.split('\n')
    lines = []
    for para in paragraphs:
        para = para.strip()
        if not para:
            lines.append('')
            continue
        words = para.split(' ')
        curr = ''
        for w in words:
            candidate = w if not curr else f"{curr} {w}"
            if text_width(candidate, font_size, font) <= max_width:
                curr = candidate
            else:
                if curr:
                    lines.append(curr)
                curr = w
        if curr:
            lines.append(curr)
    return lines


def get_jpeg_dimensions(path: str):
    with open(path, 'rb') as f:
        data = f.read()
    idx = 2
    while idx < len(data):
        marker = data[idx:idx+2]
        if len(marker) < 2:
            break
        if marker[0] != 0xFF:
            idx += 1
            continue
        code = marker[1]
        if code in (0xC0, 0xC1, 0xC2):
            h, w = struct.unpack('>HH', data[idx+5:idx+9])
            return w, h, data
        elif code in (0xD8, 0xD9, 0x01) or (0xD0 <= code <= 0xD7):
            idx += 2
        else:
            seg_len = struct.unpack('>H', data[idx+2:idx+4])[0]
            idx += 2 + seg_len
    raise ValueError(f"Could not parse JPEG dimensions for {path}")


class PDFDocument:
    def __init__(self):
        self.pages = []
        self.images = {}
        self.current_cmds = []
        self.current_annots = []

    def load_image(self, name: str, path: str):
        w, h, data = get_jpeg_dimensions(path)
        self.images[name] = (w, h, data)

    def new_page(self):
        if self.current_cmds:
            self.pages.append((b'\n'.join(self.current_cmds), list(self.current_annots)))
        self.current_cmds = []
        self.current_annots = []

    def finish_current_page(self):
        if self.current_cmds:
            self.pages.append((b'\n'.join(self.current_cmds), list(self.current_annots)))
            self.current_cmds = []
            self.current_annots = []

    def py(self, top_y: float) -> float:
        return PAGE_H - top_y

    def add_cmd(self, s: str):
        self.current_cmds.append(s.encode('latin1'))

    def set_fill(self, rgb):
        self.add_cmd(f"{rgb[0]:.3f} {rgb[1]:.3f} {rgb[2]:.3f} rg")

    def set_stroke(self, rgb, lw=1.0):
        self.add_cmd(f"{lw:.2f} w {rgb[0]:.3f} {rgb[1]:.3f} {rgb[2]:.3f} RG")

    def rect(self, x, top_y, w, h, fill=None, stroke=None, lw=1.0):
        y = PAGE_H - top_y - h
        self.add_cmd("q")
        if fill:
            self.set_fill(fill)
        if stroke:
            self.set_stroke(stroke, lw)
        self.add_cmd(f"{x:.2f} {y:.2f} {w:.2f} {h:.2f} re")
        if fill and stroke:
            self.add_cmd("B")
        elif fill:
            self.add_cmd("f")
        elif stroke:
            self.add_cmd("S")
        self.add_cmd("Q")

    def rounded_rect(self, x, top_y, w, h, r=8.0, fill=None, stroke=None, lw=1.0):
        r = min(r, w / 2.0, h / 2.0)
        y0 = PAGE_H - top_y - h
        y1 = PAGE_H - top_y
        x0 = x
        x1 = x + w
        k = 0.55228475 * r
        self.add_cmd("q")
        if fill:
            self.set_fill(fill)
        if stroke:
            self.set_stroke(stroke, lw)
        self.add_cmd(f"{x0 + r:.2f} {y0:.2f} m")
        self.add_cmd(f"{x1 - r:.2f} {y0:.2f} l")
        self.add_cmd(f"{x1 - r + k:.2f} {y0:.2f} {x1:.2f} {y0 + r - k:.2f} {x1:.2f} {y0 + r:.2f} c")
        self.add_cmd(f"{x1:.2f} {y1 - r:.2f} l")
        self.add_cmd(f"{x1:.2f} {y1 - r + k:.2f} {x1 - r + k:.2f} {y1:.2f} {x1 - r:.2f} {y1:.2f} c")
        self.add_cmd(f"{x0 + r:.2f} {y1:.2f} l")
        self.add_cmd(f"{x0 + r - k:.2f} {y1:.2f} {x0:.2f} {y1 - r + k:.2f} {x0:.2f} {y1 - r:.2f} c")
        self.add_cmd(f"{x0:.2f} {y0 + r:.2f} l")
        self.add_cmd(f"{x0:.2f} {y0 + r - k:.2f} {x0 + r - k:.2f} {y0:.2f} {x0 + r:.2f} {y0:.2f} c")
        self.add_cmd("h")
        if fill and stroke:
            self.add_cmd("B")
        elif fill:
            self.add_cmd("f")
        elif stroke:
            self.add_cmd("S")
        self.add_cmd("Q")

    def circle(self, cx, top_cy, r, fill=None, stroke=None, lw=1.0):
        cy = PAGE_H - top_cy
        k = 0.55228475 * r
        self.add_cmd("q")
        if fill:
            self.set_fill(fill)
        if stroke:
            self.set_stroke(stroke, lw)
        self.add_cmd(f"{cx + r:.2f} {cy:.2f} m")
        self.add_cmd(f"{cx + r:.2f} {cy + k:.2f} {cx + k:.2f} {cy + r:.2f} {cx:.2f} {cy + r:.2f} c")
        self.add_cmd(f"{cx - k:.2f} {cy + r:.2f} {cx - r:.2f} {cy + k:.2f} {cx - r:.2f} {cy:.2f} c")
        self.add_cmd(f"{cx - r:.2f} {cy - k:.2f} {cx - k:.2f} {cy - r:.2f} {cx:.2f} {cy - r:.2f} c")
        self.add_cmd(f"{cx + k:.2f} {cy - r:.2f} {cx + r:.2f} {cy - k:.2f} {cx + r:.2f} {cy:.2f} c")
        self.add_cmd("h")
        if fill and stroke:
            self.add_cmd("B")
        elif fill:
            self.add_cmd("f")
        elif stroke:
            self.add_cmd("S")
        self.add_cmd("Q")

    def line(self, x1, top_y1, x2, top_y2, stroke=BORDER, lw=0.8):
        self.add_cmd("q")
        self.set_stroke(stroke, lw)
        self.add_cmd(f"{x1:.2f} {self.py(top_y1):.2f} m {x2:.2f} {self.py(top_y2):.2f} l S")
        self.add_cmd("Q")

    def draw_image(self, img_name, x, top_y, w, h):
        y = PAGE_H - top_y - h
        self.add_cmd("q")
        self.add_cmd(f"{w:.2f} 0 0 {h:.2f} {x:.2f} {y:.2f} cm")
        self.add_cmd(f"/{img_name} Do")
        self.add_cmd("Q")

    def draw_text(self, text, x, top_y, size=10.0, color=TEXT_BODY, font='F1', align='left'):
        if align == 'center':
            w = text_width(text, size, font)
            x = x - w / 2.0
        elif align == 'right':
            w = text_width(text, size, font)
            x = x - w
        y = PAGE_H - top_y
        esc = pdf_escape(text)
        self.add_cmd("q")
        self.set_fill(color)
        self.current_cmds.append(
            f"BT /{font} {size:.2f} Tf {x:.2f} {y:.2f} Td (".encode('latin1') + esc + b") Tj ET"
        )
        self.add_cmd("Q")

    def draw_paragraph(self, text, x, top_y, max_width, size=9.0, leading=13.0, color=TEXT_BODY, font='F1', align='left'):
        lines = wrap_text(text, max_width, size, font)
        curr_y = top_y
        for ln in lines:
            if ln == '':
                curr_y += leading * 0.55
                continue
            tx = x
            if align == 'center':
                tx = x + max_width / 2.0
            elif align == 'right':
                tx = x + max_width
            self.draw_text(ln, tx, curr_y, size=size, color=color, font=font, align=align)
            curr_y += leading
        return curr_y

    def add_link(self, x, top_y, w, h, uri):
        y0 = PAGE_H - top_y - h
        y1 = PAGE_H - top_y
        self.current_annots.append((x, y0, x + w, y1, uri))

    def save(self, out_path: str):
        self.finish_current_page()
        objs = []

        def add_obj(content_bytes: bytes) -> int:
            objs.append(content_bytes)
            return len(objs)

        # Embed TrueType fonts (DejaVuSans, DejaVuSans-Bold, DejaVuSerif)
        font_ids = {}
        font_names = {'F1': 'DejaVuSans', 'F2': 'DejaVuSans-Bold', 'F3': 'DejaVuSerif'}
        for key, base_name in font_names.items():
            m = FONT_METRICS[key]
            comp = m['compressed']
            raw_len = len(m['raw'])
            ff_header = f"<< /Length {len(comp)} /Length1 {raw_len} /Filter /FlateDecode >>\nstream\n".encode('latin1')
            ff_id = add_obj(ff_header + comp + b"\nendstream")

            bbox = m['bbox']
            fd_id = add_obj((
                f"<< /Type /FontDescriptor /FontName /{base_name} /Flags 32 "
                f"/FontBBox [{bbox[0]} {bbox[1]} {bbox[2]} {bbox[3]}] "
                f"/ItalicAngle 0 /Ascent {m['ascent']} /Descent {m['descent']} "
                f"/CapHeight 729 /StemV 80 /FontFile2 {ff_id} 0 R >>"
            ).encode('latin1'))

            widths_str = " ".join(str(w) for w in m['widths'])
            font_obj_id = add_obj((
                f"<< /Type /Font /Subtype /TrueType /BaseFont /{base_name} "
                f"/FirstChar 32 /LastChar 255 /Widths [{widths_str}] "
                f"/FontDescriptor {fd_id} 0 R /Encoding /WinAnsiEncoding >>"
            ).encode('latin1'))
            font_ids[key] = font_obj_id

        # Embed JPEG XObjects
        img_obj_ids = {}
        for name, (w, h, data) in self.images.items():
            header = (
                f"<< /Type /XObject /Subtype /Image /Width {w} /Height {h} "
                f"/ColorSpace /DeviceRGB /BitsPerComponent 8 /Filter /DCTDecode "
                f"/Length {len(data)} >>\nstream\n"
            ).encode('latin1')
            img_obj_ids[name] = add_obj(header + data + b"\nendstream")

        xobj_entries = " ".join(f"/{name} {oid} 0 R" for name, oid in img_obj_ids.items())
        resources_str = (
            f"<< /Font << /F1 {font_ids['F1']} 0 R /F2 {font_ids['F2']} 0 R /F3 {font_ids['F3']} 0 R >> "
            f"/XObject << {xobj_entries} >> >>"
        )

        pages_tree_id = add_obj(b"PLACEHOLDER_PAGES")
        catalog_id = add_obj(f"<< /Type /Catalog /Pages {pages_tree_id} 0 R >>".encode('latin1'))

        page_ids = []
        for stream_bytes, annots in self.pages:
            comp_stream = zlib.compress(stream_bytes)
            content_header = f"<< /Length {len(comp_stream)} /Filter /FlateDecode >>\nstream\n".encode('latin1')
            content_id = add_obj(content_header + comp_stream + b"\nendstream")

            annot_ids = []
            for (x0, y0, x1, y1, uri) in annots:
                a_bytes = (
                    f"<< /Type /Annot /Subtype /Link /Rect [{x0:.2f} {y0:.2f} {x1:.2f} {y1:.2f}] "
                    f"/Border [0 0 0] /A << /S /URI /URI ({uri}) >> >>"
                ).encode('latin1')
                annot_ids.append(add_obj(a_bytes))

            annots_str = " /Annots [" + " ".join(f"{aid} 0 R" for aid in annot_ids) + "]" if annot_ids else ""
            page_dict = (
                f"<< /Type /Page /Parent {pages_tree_id} 0 R "
                f"/MediaBox [0 0 {PAGE_W:.2f} {PAGE_H:.2f}] "
                f"/Resources {resources_str} "
                f"/Contents {content_id} 0 R{annots_str} >>"
            ).encode('latin1')
            page_ids.append(add_obj(page_dict))

        kids_str = " ".join(f"{pid} 0 R" for pid in page_ids)
        objs[pages_tree_id - 1] = f"<< /Type /Pages /Kids [{kids_str}] /Count {len(page_ids)} >>".encode('latin1')

        buf = bytearray()
        buf.extend(b"%PDF-1.4\n%\xe2\xe3\xcf\xd3\n")
        offsets = [0]
        for idx, ob in enumerate(objs, start=1):
            offsets.append(len(buf))
            buf.extend(f"{idx} 0 obj\n".encode('latin1'))
            buf.extend(ob)
            buf.extend(b"\nendobj\n")

        xref_pos = len(buf)
        buf.extend(f"xref\n0 {len(objs) + 1}\n".encode('latin1'))
        buf.extend(b"0000000000 65535 f \n")
        for off in offsets[1:]:
            buf.extend(f"{off:010d} 00000 n \n".encode('latin1'))

        buf.extend((
            f"trailer\n<< /Size {len(objs) + 1} /Root {catalog_id} 0 R >>\n"
            f"startxref\n{xref_pos}\n%%EOF\n"
        ).encode('latin1'))

        with open(out_path, 'wb') as f:
            f.write(buf)
        print(f"PDF generated: {out_path} ({len(buf) // 1024} KB, {len(page_ids)} pages)")


def draw_header_footer(pdf: PDFDocument, page_num: int, total_pages: int, section_tag: str):
    pdf.rect(0, 0, PAGE_W, 44, fill=NAVY)
    pdf.rect(0, 44, PAGE_W, 3, fill=BLUE)
    pdf.draw_image('logo', 36, 9, 26, 26)
    pdf.draw_text("EL VITA", 70, 22, size=10.5, color=WHITE, font='F2')
    pdf.draw_text("Compagnon Clinique & Pédagogique", 70, 34, size=7.5, color=BLUE_LIGHT, font='F1')
    pdf.draw_text(section_tag.upper(), PAGE_W - 36, 27, size=8.0, color=BLUE_LIGHT, font='F2', align='right')

    pdf.line(36, PAGE_H - 36, PAGE_W - 36, PAGE_H - 36, stroke=BORDER, lw=0.7)
    pdf.draw_text("EL VITA — Dossier Officiel de Présentation & Architecture | Albert DOSSOU (Fondateur & Propriétaire)", 36, PAGE_H - 22, size=7.4, color=TEXT_MUTED, font='F1')
    pdf.draw_text(f"Page {page_num} / {total_pages}", PAGE_W - 36, PAGE_H - 22, size=7.8, color=NAVY, font='F2', align='right')


def section_heading(pdf: PDFDocument, num_label: str, title: str, subtitle: str, x: float, y: float) -> float:
    pdf.rounded_rect(x, y, 26, 22, r=5, fill=BLUE)
    pdf.draw_text(num_label, x + 13, y + 15, size=10.0, color=WHITE, font='F2', align='center')
    pdf.draw_text(title, x + 34, y + 12, size=13.5, color=NAVY, font='F2')
    if subtitle:
        pdf.draw_text(subtitle, x + 34, y + 25, size=8.3, color=TEXT_MUTED, font='F3')
        return y + 36
    return y + 30


def bullet_item(pdf: PDFDocument, x: float, y: float, max_w: float, lead_bold: str, rest: str, dot_color=BLUE, size=8.3, leading=12.0) -> float:
    pdf.circle(x + 4, y - 3, 2.3, fill=dot_color)
    full_text = f"{lead_bold} {rest}".strip() if lead_bold else rest
    if lead_bold and text_width(lead_bold, size, 'F2') < max_w * 0.55:
        lw = text_width(lead_bold + " ", size, 'F2')
        first_line_max = max_w - 14 - lw
        words = rest.split(' ')
        first_words = []
        idx = 0
        curr = ''
        while idx < len(words):
            cand = words[idx] if not curr else curr + ' ' + words[idx]
            if text_width(cand, size, 'F1') <= first_line_max:
                curr = cand
                first_words.append(words[idx])
                idx += 1
            else:
                break
        remaining = " ".join(words[idx:])
        pdf.draw_text(lead_bold, x + 13, y, size=size, color=TEXT_DARK, font='F2')
        if curr:
            pdf.draw_text(curr, x + 13 + lw, y, size=size, color=TEXT_BODY, font='F1')
        next_y = y + leading
        if remaining:
            next_y = pdf.draw_paragraph(remaining, x + 13, next_y, max_w - 14, size=size, leading=leading, color=TEXT_BODY, font='F1')
        return next_y + 2
    else:
        return pdf.draw_paragraph(full_text, x + 13, y, max_w - 14, size=size, leading=leading, color=TEXT_BODY, font='F1') + 2


def build_dossier():
    pdf = PDFDocument()
    for name, fname in [
        ('logo', 'logo.jpg'),
        ('founder', 'founder.jpg'),
        ('banner', 'banner.jpg'),
        ('scr1', 'scr1_home.jpg'),
        ('scr2', 'scr2_vitals.jpg'),
        ('scr3', 'scr3_clinical.jpg'),
        ('scr4', 'scr4_labs.jpg'),
        ('scr5', 'scr5_semiology.jpg'),
    ]:
        pdf.load_image(name, os.path.join('/tmp/pdf_assets', fname))

    TOTAL_PAGES = 8

    # =========================================================================
    # PAGE 1 : PAGE DE GARDE OFFICIELLE
    # =========================================================================
    pdf.new_page()
    pdf.rect(0, 0, PAGE_W, 326, fill=NAVY)
    pdf.rect(0, 326, PAGE_W, 6, fill=BLUE)

    pdf.rounded_rect(42, 30, 260, 22, r=11, fill=NAVY_MID, stroke=BLUE, lw=1.0)
    pdf.draw_text("DOSSIER DE PRÉSENTATION OFFICIELLE", 172, 44.5, size=8.0, color=BLUE_LIGHT, font='F2', align='center')

    pdf.rounded_rect(42, 70, 72, 72, r=14, fill=NAVY_MID, stroke=BLUE_LIGHT, lw=1.5)
    pdf.draw_image('logo', 46, 74, 64, 64)

    pdf.draw_text("EL VITA", 130, 102, size=31, color=WHITE, font='F2')
    pdf.draw_text("« Apprendre. Comprendre. Pratiquer. »", 130, 124, size=12, color=BLUE_LIGHT, font='F2')
    pdf.draw_text("Plateforme Numérique d'Apprentissage Médical & Compagnon Clinique", 130, 141, size=9.2, color=(203/255, 213/255, 225/255), font='F1')

    pdf.line(42, 162, PAGE_W - 42, 162, stroke=(30/255, 58/255, 138/255), lw=1.0)
    pdf.draw_paragraph(
        "Présentation Institutionnelle, Architecture Pédagogique & Guide de Déploiement Multiplateforme (Android & iPhone)",
        42, 186, PAGE_W - 84, size=14.8, leading=21, color=WHITE, font='F2'
    )
    pdf.draw_paragraph(
        "Une solution clinique 100% hors-ligne, gratuite et respectueuse du secret médical, conçue pour accompagner les étudiants en médecine de la 3e à la 7e année au lit du malade.",
        42, 240, PAGE_W - 84, size=9.8, leading=14.5, color=(186/255, 230/255, 253/255), font='F1'
    )

    pdf.rounded_rect(42, 286, 175, 24, r=12, fill=GREEN_DARK, stroke=GREEN, lw=1.0)
    pdf.draw_text("100% HORS-LIGNE & GRATUIT", 129.5, 301.5, size=8.0, color=WHITE, font='F2', align='center')
    pdf.rounded_rect(228, 286, 195, 24, r=12, fill=NAVY_MID, stroke=BLUE_LIGHT, lw=1.0)
    pdf.draw_text("ANDROID (.APK) & IPHONE (PWA)", 325.5, 301.5, size=8.0, color=BLUE_LIGHT, font='F2', align='center')

    # Hero banner card
    pdf.rounded_rect(42, 350, PAGE_W - 84, 246, r=12, fill=WHITE, stroke=BORDER, lw=1.0)
    img_w = 428
    img_h = 239
    pdf.draw_image('banner', (PAGE_W - img_w) / 2, 353, img_w, img_h)

    # 4 KPI Cards
    kpis = [
        ("5", "Modules Cliniques", "Intégrés & Complets", BLUE),
        ("29", "Fiches Sémiologie", "14 Rubriques Chacune", TEAL),
        ("90", "QCM Officiels", "8 Appareils Médicaux", GREEN),
        ("14", "Étapes Anamnèse", "Formation Clinique", NAVY),
    ]
    card_w = (PAGE_W - 84 - 30) / 4
    ky = 614
    for idx, (val, t1, t2, col) in enumerate(kpis):
        cx = 42 + idx * (card_w + 10)
        pdf.rounded_rect(cx, ky, card_w, 74, r=8, fill=BG_LIGHT, stroke=BORDER, lw=0.8)
        pdf.rect(cx, ky, card_w, 4, fill=col)
        pdf.draw_text(val, cx + card_w / 2, ky + 30, size=19, color=col, font='F2', align='center')
        pdf.draw_text(t1, cx + card_w / 2, ky + 48, size=8.0, color=TEXT_DARK, font='F2', align='center')
        pdf.draw_text(t2, cx + card_w / 2, ky + 61, size=7.2, color=TEXT_MUTED, font='F1', align='center')

    # Founder Card at bottom of cover
    pdf.rounded_rect(42, 706, PAGE_W - 84, 100, r=10, fill=BLUE_PALE, stroke=BLUE, lw=1.2)
    pdf.draw_image('founder', 54, 715, 54, 81)
    pdf.draw_text("INITIATIVE INDÉPENDANTE PORTÉE PAR :", 122, 728, size=7.8, color=BLUE, font='F2')
    pdf.draw_text("ALBERT DOSSOU", 122, 747, size=14.5, color=NAVY, font='F2')
    pdf.draw_text("Fondateur & Propriétaire de la Plateforme EL VITA", 122, 763, size=9.0, color=TEXT_DARK, font='F2')
    pdf.draw_text("Contact Officiel : dossoualbert00@gmail.com", 122, 779, size=8.4, color=BLUE, font='F1')
    pdf.draw_text("Accès iOS / Web : https://dossoualbert00-commits.github.io/elvita/", 122, 794, size=8.4, color=BLUE, font='F1')
    pdf.add_link(122, 769, 280, 12, "mailto:dossoualbert00@gmail.com")
    pdf.add_link(122, 784, 380, 12, "https://dossoualbert00-commits.github.io/elvita/")

    # =========================================================================
    # PAGE 2 : LE FONDATEUR, HISTOIRE, VISION & MISSION
    # =========================================================================
    pdf.new_page()
    draw_header_footer(pdf, 2, TOTAL_PAGES, "Identité, Fondateur & Vision")

    y = section_heading(pdf, "01", "Le Fondateur & Direction du Projet", "Engagement indépendant au service de la formation médicale", 36, 62)

    # Left Column: Portrait
    pdf.rounded_rect(36, y, 198, 404, r=12, fill=NAVY, stroke=BLUE, lw=1.5)
    pdf.rounded_rect(50, y + 12, 170, 20, r=10, fill=NAVY_MID, stroke=BLUE_LIGHT, lw=0.8)
    pdf.draw_text("DIRECTION & PROPRIÉTÉ", 135, y + 25, size=7.4, color=BLUE_LIGHT, font='F2', align='center')

    pdf.rounded_rect(49, y + 40, 172, 256, r=10, fill=NAVY_MID, stroke=BLUE_LIGHT, lw=1.2)
    pdf.draw_image('founder', 51, y + 42, 168, 252)

    pdf.draw_text("ALBERT DOSSOU", 135, y + 318, size=13.5, color=WHITE, font='F2', align='center')
    pdf.draw_text("Fondateur & Propriétaire d'El Vita", 135, y + 334, size=8.2, color=BLUE_LIGHT, font='F2', align='center')
    pdf.line(56, y + 345, 214, y + 345, stroke=(30/255, 58/255, 138/255), lw=0.8)
    pdf.draw_text("Plateforme indépendante", 135, y + 360, size=7.8, color=(203/255, 213/255, 225/255), font='F1', align='center')
    pdf.draw_text("Médecine 3e à 7e année", 135, y + 374, size=7.8, color=(203/255, 213/255, 225/255), font='F1', align='center')
    pdf.draw_text("dossoualbert00@gmail.com", 135, y + 389, size=7.8, color=BLUE_LIGHT, font='F2', align='center')

    # Right Column
    rx = 248
    rw = PAGE_W - 36 - rx

    pdf.rounded_rect(rx, y, rw, 140, r=10, fill=BLUE_PALE, stroke=BLUE, lw=1.0)
    pdf.draw_text("MOT DU FONDATEUR", rx + 14, y + 18, size=8.8, color=BLUE, font='F2')
    pdf.draw_paragraph(
        "« El Vita est une initiative indépendante portée par Albert Dossou, son fondateur et propriétaire.\n"
        "À travers ce projet, il souhaite contribuer à rendre l'apprentissage médical plus accessible et à mettre les outils numériques au service de la formation des futurs professionnels de santé.\n"
        "El Vita est développée avec une ambition : proposer aux étudiants en médecine un environnement d'apprentissage utile, pratique et évolutif. »",
        rx + 14, y + 34, rw - 28, size=8.2, leading=11.8, color=TEXT_DARK, font='F3'
    )

    hy = y + 150
    pdf.rounded_rect(rx, hy, rw, 132, r=10, fill=BG_LIGHT, stroke=BORDER, lw=0.9)
    pdf.draw_text("NOTRE HISTOIRE & LE CONSTAT DE TERRAIN", rx + 14, hy + 18, size=8.8, color=NAVY, font='F2')
    cy_h = pdf.draw_paragraph(
        "El Vita est née du constat quotidien vécu par les étudiants en médecine lors des stages hospitaliers et gardes :",
        rx + 14, hy + 33, rw - 28, size=8.1, leading=11.5, color=TEXT_BODY, font='F1'
    )
    by = bullet_item(pdf, rx + 14, cy_h + 4, rw - 26, "1. Zones blanches :", "Absence fréquente de réseau dans les services.", size=8.0, leading=11.2)
    by = bullet_item(pdf, rx + 14, by, rw - 26, "2. Dispersion :", "Sémiologie, constantes et biologie fragmentées.", size=8.0, leading=11.2)
    by = bullet_item(pdf, rx + 14, by, rw - 26, "3. Secret médical :", "Zéro donnée patient sur des serveurs cloud.", size=8.0, leading=11.2)

    my = hy + 142
    pdf.rounded_rect(rx, my, rw, 112, r=10, fill=BG_LIGHT, stroke=BORDER, lw=0.9)
    pdf.draw_text("NOTRE MISSION & NOTRE VISION", rx + 14, my + 18, size=8.8, color=NAVY, font='F2')
    pdf.draw_paragraph(
        "Faciliter la compréhension des signes cliniques, l'interprétation des examens complémentaires et la maîtrise du raisonnement médical.\n"
        "Notre vision : faire de chaque téléphone un compagnon clinique fiable, rigoureux et universel, gratuit pour les externes sur Android et iPhone.",
        rx + 14, my + 34, rw - 28, size=8.1, leading=11.8, color=TEXT_BODY, font='F1'
    )

    # Free access box
    fy = y + 418
    pdf.rounded_rect(36, fy, PAGE_W - 72, 78, r=10, fill=GREEN_PALE, stroke=GREEN, lw=1.2)
    pdf.rounded_rect(48, fy + 12, 132, 18, r=9, fill=GREEN)
    pdf.draw_text("GRATUIT ACTUELLEMENT", 114, fy + 24, size=7.2, color=WHITE, font='F2', align='center')
    pdf.draw_text("ACCÈS INTÉGRAL SANS PAIEMENT NI PUBLICITÉ", 190, fy + 24, size=9.2, color=GREEN_DARK, font='F2')
    pdf.draw_paragraph(
        "Dans sa phase actuelle de déploiement universitaire, EL VITA est accessible gratuitement aux étudiants en médecine. "
        "L'ensemble des ressources (dossiers, formation en 14 points, calculateurs biologiques, 29 fiches sémiologiques et 90 QCM) est disponible sans publicité.",
        48, fy + 41, PAGE_W - 96, size=8.2, leading=11.8, color=TEXT_DARK, font='F1'
    )

    # Technical Table
    ty = fy + 94
    pdf.draw_text("FICHE SIGNALÉTIQUE RÉCAPITULATIVE", 36, ty, size=9.0, color=NAVY, font='F2')
    rows = [
        ("Plateforme", "EL VITA — Compagnon Clinique", "Version actuelle", "1.0.0 (Android + iOS PWA)"),
        ("Propriétaire", "Albert DOSSOU (Fondateur)", "Modèle d'accès", "100% Gratuit, Sans Publicité"),
        ("Connectivité", "100% Hors-ligne (Offline-First)", "Confidentialité", "Stockage 100% local isolé"),
    ]
    ry = ty + 8
    for i, (k1, v1, k2, v2) in enumerate(rows):
        bg = BG_LIGHT if i % 2 == 0 else WHITE
        pdf.rect(36, ry, PAGE_W - 72, 22, fill=bg, stroke=BORDER, lw=0.6)
        pdf.draw_text(k1 + " :", 44, ry + 14.5, size=7.8, color=BLUE, font='F2')
        pdf.draw_text(v1, 114, ry + 14.5, size=7.8, color=TEXT_DARK, font='F1')
        pdf.draw_text(k2 + " :", 318, ry + 14.5, size=7.8, color=BLUE, font='F2')
        pdf.draw_text(v2, 398, ry + 14.5, size=7.8, color=TEXT_DARK, font='F1')
        ry += 22

    # =========================================================================
    # PAGE 3 : ARCHITECTURE TECHNIQUE & ÉQUIVALENCE ANDROID / IPHONE
    # =========================================================================
    pdf.new_page()
    draw_header_footer(pdf, 3, TOTAL_PAGES, "Architecture & Multiplateforme")

    y = section_heading(pdf, "02", "Architecture Technique & Parité Multiplateforme", "Une expérience clinique strictement identique sur Android et iPhone", 36, 62)

    py_intro = pdf.draw_paragraph(
        "Pour garantir qu'aucun étudiant ne soit exclu en raison de son téléphone, EL VITA repose sur une architecture double offrant strictement les mêmes écrans, les mêmes algorithmes de calcul clinique et la même base de connaissances médicales sur Android et iOS (iPhone).",
        36, y + 6, PAGE_W - 72, size=8.8, leading=13, color=TEXT_BODY, font='F1'
    )

    cy = py_intro + 8
    half_w = (PAGE_W - 72 - 14) / 2

    pdf.rounded_rect(36, cy, half_w, 168, r=10, fill=WHITE, stroke=GREEN, lw=1.2)
    pdf.rect(36, cy, half_w, 28, fill=GREEN_DARK)
    pdf.draw_text("APPLICATION NATIVE ANDROID (.APK)", 36 + half_w / 2, cy + 18, size=8.8, color=WHITE, font='F2', align='center')
    ay = cy + 44
    ay = bullet_item(pdf, 46, ay, half_w - 20, "Technologie :", "Kotlin moderne + Jetpack Compose (Material 3).", dot_color=GREEN, size=8.0, leading=11.4)
    ay = bullet_item(pdf, 46, ay, half_w - 20, "Base locale :", "SQLite / Room chiffrée sur le téléphone.", dot_color=GREEN, size=8.0, leading=11.4)
    ay = bullet_item(pdf, 46, ay, half_w - 20, "Diffusion :", "Fichier installable EL VITA.apk via WhatsApp.", dot_color=GREEN, size=8.0, leading=11.4)
    ay = bullet_item(pdf, 46, ay, half_w - 20, "Hors-ligne :", "Autonome sans Wi-Fi ni données mobiles.", dot_color=GREEN, size=8.0, leading=11.4)

    ix = 36 + half_w + 14
    pdf.rounded_rect(ix, cy, half_w, 168, r=10, fill=WHITE, stroke=BLUE, lw=1.2)
    pdf.rect(ix, cy, half_w, 28, fill=NAVY)
    pdf.draw_text("APPLICATION IPHONE / iOS (PWA SAFARI)", ix + half_w / 2, cy + 18, size=8.8, color=BLUE_LIGHT, font='F2', align='center')
    iy = cy + 44
    iy = bullet_item(pdf, ix + 10, iy, half_w - 20, "Fidélité 1:1 :", "Même maquette, mêmes 29 fiches et 90 QCM.", dot_color=BLUE, size=8.0, leading=11.4)
    iy = bullet_item(pdf, ix + 10, iy, half_w - 20, "Service Worker :", "Cache complet pour usage 100% hors-ligne.", dot_color=BLUE, size=8.0, leading=11.4)
    iy = bullet_item(pdf, ix + 10, iy, half_w - 20, "Installation :", "Via Safari -> Sur l'écran d'accueil.", dot_color=BLUE, size=8.0, leading=11.4)
    iy = bullet_item(pdf, ix + 10, iy, half_w - 20, "Plein écran :", "Icône officielle iOS sans barre navigateur.", dot_color=BLUE, size=8.0, leading=11.4)

    gy = cy + 186
    pdf.draw_text("LES 4 GARANTIES FONDAMENTALES DU CAHIER DES CHARGES", 36, gy, size=9.6, color=NAVY, font='F2')

    pillars = [
        ("1. MOTEUR CLINIQUE DÉTERMINISTE (FIABILITÉ MÉDICALE)",
         "Tous les calculs (PAM, IMC, Cockcroft-Gault, NFS 6 volets, Score de Glasgow) et seuils d'alerte reposent sur des formules médicales validées et codées en dur — sans dépendance à une IA probabiliste ni connexion cloud.",
         BLUE_PALE, BLUE),
        ("2. SECRET MÉDICAL & PROTECTION DES DONNÉES PATIENT",
         "Conformément aux exigences hospitalières, aucun nom de patient, âge ou constante ne transite par internet. Toutes les observations restent confinées dans l'espace sécurisé local du smartphone de l'étudiant.",
         GREEN_PALE, GREEN),
        ("3. ACCESSIBILITÉ ERGONOMIQUE AU LIT DU MALADE",
         "Mode sombre médical natif réduisant la fatigue visuelle lors des gardes de nuit, navigation par 5 onglets principaux et alertes colorées instantanées (Rouge = Urgence vitale, Orange = Vigilance, Vert = Normal).",
         TEAL_PALE, TEAL),
        ("4. CONTENU PÉDAGOGIQUE CONSOLIDÉ & VÉRIFIABLE",
         "Référentiel complet réunissant 6 fiches de santé publique, 14 compétences d'interrogatoire, 9 rubriques d'organisation de dossier, 29 fiches sémiologiques (14 sections chacune) et 90 QCM justifiés.",
         ORANGE_PALE, ORANGE),
    ]

    py = gy + 14
    for title, desc, bg, border_c in pillars:
        pdf.rounded_rect(36, py, PAGE_W - 72, 66, r=8, fill=bg, stroke=border_c, lw=0.9)
        pdf.rect(36, py + 8, 4, 50, fill=border_c)
        pdf.draw_text(title, 50, py + 20, size=8.6, color=NAVY, font='F2')
        pdf.draw_paragraph(desc, 50, py + 34, PAGE_W - 102, size=8.0, leading=11.4, color=TEXT_BODY, font='F1')
        py += 74

    pdf.draw_text("CARTOGRAPHIE DES 5 MODULES DE LA BARRE DE NAVIGATION", 36, py + 8, size=9.0, color=NAVY, font='F2')
    mods = [
        ("1. Accueil", "Dossiers, Profil, 6 fiches Prévention & Accès rapide"),
        ("2. Clinique", "Formation 14 pts, Observation & Suivi Jx"),
        ("3. Mesures", "8 Constantes, PAM/IMC, Alertes & Courbes SVG"),
        ("4. Analyses", "NFS, Rein, Foie, Hémostase & Score Glasgow"),
        ("5. Sémiologie", "29 fiches cliniques & Banque de 90 QCM"),
    ]
    my = py + 18
    col_w = (PAGE_W - 72) / 5
    for idx, (m_title, m_desc) in enumerate(mods):
        mx = 36 + idx * col_w
        pdf.rect(mx, my, col_w, 60, fill=WHITE, stroke=BORDER, lw=0.6)
        pdf.rect(mx, my, col_w, 19, fill=NAVY_MID)
        pdf.draw_text(m_title, mx + col_w / 2, my + 12.5, size=7.8, color=WHITE, font='F2', align='center')
        pdf.draw_paragraph(m_desc, mx + 5, my + 29, col_w - 10, size=6.9, leading=9.5, color=TEXT_BODY, font='F1', align='center')

    # =========================================================================
    # PAGE 4 : MODULES 1 & 2 (ACCUEIL, PRÉVENTION & CLINIQUE) + CAPTURES
    # =========================================================================
    pdf.new_page()
    draw_header_footer(pdf, 4, TOTAL_PAGES, "Modules 1 & 2 : Accueil & Clinique")

    y = section_heading(pdf, "03", "Modules Accueil, Prévention & Clinique", "Dossiers patients, fiches de santé publique et formation à l'interrogatoire", 36, 62)

    h_scr = 228
    w_scr1 = h_scr * (302 / 650)
    w_scr_clin = h_scr * (339 / 641)

    pdf.rounded_rect(36, y, 256, 356, r=10, fill=BG_LIGHT, stroke=BORDER, lw=0.9)
    pdf.rect(36, y, 256, 24, fill=BLUE)
    pdf.draw_text("MODULE 1 : ACCUEIL & PRÉVENTION", 164, y + 16, size=8.5, color=WHITE, font='F2', align='center')

    sx1 = 36 + (256 - w_scr1) / 2
    pdf.rounded_rect(sx1 - 3, y + 32, w_scr1 + 6, h_scr + 6, r=8, fill=NAVY)
    pdf.draw_image('scr1', sx1, y + 35, w_scr1, h_scr)

    pdf.draw_text("Fonctionnalités clés de l'Accueil :", 46, y + 278, size=8.2, color=NAVY, font='F2')
    ly = y + 292
    ly = bullet_item(pdf, 46, ly, 236, "Header :", "Dossiers patients & Profil/À propos.", size=7.5, leading=10.5)
    ly = bullet_item(pdf, 46, ly, 236, "Vigilance :", "Carrousel de 6 fiches de santé publique.", size=7.5, leading=10.5)
    ly = bullet_item(pdf, 46, ly, 236, "Accès rapide :", "Grille 2x2 vers les modules.", size=7.5, leading=10.5)

    rx = 304
    rw = PAGE_W - 36 - rx
    pdf.rounded_rect(rx, y, rw, 356, r=10, fill=BG_LIGHT, stroke=BORDER, lw=0.9)
    pdf.rect(rx, y, rw, 24, fill=NAVY_MID)
    pdf.draw_text("MODULE 2 : CLINIQUE & OBSERVATION", rx + rw / 2, y + 16, size=8.5, color=WHITE, font='F2', align='center')

    sx3 = rx + (rw - w_scr_clin) / 2
    pdf.rounded_rect(sx3 - 3, y + 32, w_scr_clin + 6, h_scr + 6, r=8, fill=NAVY)
    pdf.draw_image('scr2', sx3, y + 35, w_scr_clin, h_scr)

    pdf.draw_text("Fonctionnalités clés du Module Clinique :", rx + 10, y + 278, size=8.2, color=NAVY, font='F2')
    ry = y + 292
    ry = bullet_item(pdf, rx + 10, ry, rw - 20, "Formation 14 pts :", "Interrogatoire & exercices.", size=7.5, leading=10.5)
    ry = bullet_item(pdf, rx + 10, ry, rw - 20, "Observation :", "Formulaire complet par appareil.", size=7.5, leading=10.5)
    ry = bullet_item(pdf, rx + 10, ry, rw - 20, "Suivi Jx :", "Mises à jour cliniques de visite.", size=7.5, leading=10.5)

    py = y + 370
    pdf.rounded_rect(36, py, 256, 304, r=10, fill=WHITE, stroke=BLUE, lw=0.9)
    pdf.draw_text("SECTION A : LES 6 FICHES DE PRÉVENTION", 48, py + 18, size=8.5, color=BLUE, font='F2')
    preventions = [
        ("Paludisme grave :", "Goutte épaisse, TDR, critères OMS et Artésunate IV d'urgence."),
        ("Poliomyélite / PFA :", "Surveillance des paralysies flasques aiguës et 2 prélèvements de selles."),
        ("Tuberculose :", "Toux > 3 sem., hémoptysie, isolement Air FFP2 et schéma RHZE."),
        ("Rougeole :", "Signe de Koplik, éruption descendante et vaccination post-exposition."),
        ("Choléra :", "Diarrhée eau de riz et réhydratation IV urgente (Plan C OMS)."),
        ("Tétanos :", "Dépistage du trismus, parage des plaies et prophylaxie VAT/Ig."),
    ]
    pey = py + 34
    for p_t, p_d in preventions:
        pey = bullet_item(pdf, 48, pey, 234, p_t, p_d, dot_color=BLUE, size=7.7, leading=10.8)
        pey += 4

    pdf.rounded_rect(rx, py, rw, 304, r=10, fill=WHITE, stroke=TEAL, lw=0.9)
    pdf.draw_text("FORMATION CLINIQUE PÉDAGOGIQUE (4 VOLETS)", rx + 12, py + 18, size=8.4, color=TEAL, font='F2')
    training_blocks = [
        ("A. L'Interrogatoire (14 Compétences) :", "Accueil, présentation du statut d'externe, motif, histoire de la maladie chronologique, antécédents, formulations modèles, erreurs à éviter et mini-QCM pratiques."),
        ("B. L'Organisation des Données (9 Rubriques) :", "Apprendre à trier et classer chaque propos du patient dans la bonne rubrique médicale (ce qu'il faut inclure vs exclure)."),
        ("C. La Rédaction d'Observation (5 Méthodes) :", "Comparaison systématique entre une rédaction amateur et une observation hospitalière rigoureuse."),
        ("D. La Synthèse Clinique Guidée :", "Cas complets (dyspnée fébrile, douleur thoracique) pour formuler l'hypothèse principale, éliminer l'urgence et poser le plan d'action."),
    ]
    tey = py + 34
    for t_t, t_d in training_blocks:
        pdf.draw_text(t_t, rx + 12, tey, size=7.8, color=NAVY, font='F2')
        tey = pdf.draw_paragraph(t_d, rx + 12, tey + 11, rw - 24, size=7.5, leading=10.4, color=TEXT_BODY, font='F1')
        tey += 7

    # =========================================================================
    # PAGE 5 : MODULES 3 & 4 (MESURES & ANALYSES BIOLOGIQUES) + CAPTURES
    # =========================================================================
    pdf.new_page()
    draw_header_footer(pdf, 5, TOTAL_PAGES, "Modules 3 & 4 : Constantes & Biologie")

    y = section_heading(pdf, "04", "Modules Constantes Vitales & Analyses Biologiques", "Calculs cliniques déterministes, détection d'urgences et scores validés", 36, 62)

    h_scr = 228
    w_scr_vit = h_scr * (378 / 677)
    w_scr4 = h_scr * (363 / 693)

    pdf.rounded_rect(36, y, 256, 336, r=10, fill=BG_LIGHT, stroke=BORDER, lw=0.9)
    pdf.rect(36, y, 256, 24, fill=TEAL)
    pdf.draw_text("MODULE 3 : CONSTANTES & COURBES", 164, y + 16, size=8.5, color=WHITE, font='F2', align='center')
    sx2 = 36 + (256 - w_scr_vit) / 2
    pdf.rounded_rect(sx2 - 3, y + 32, w_scr_vit + 6, h_scr + 6, r=8, fill=NAVY)
    pdf.draw_image('scr3', sx2, y + 35, w_scr_vit, h_scr)
    pdf.draw_paragraph(
        "Saisie de 8 paramètres (T°, SpO2, PAS/PAD, FC, FR, Glycémie, Poids/Taille), calcul PAM & IMC, liaison aux dossiers patients et courbes évolutives.",
        46, y + 278, 236, size=7.6, leading=10.8, color=TEXT_BODY, font='F1'
    )

    pdf.rounded_rect(rx, y, rw, 336, r=10, fill=BG_LIGHT, stroke=BORDER, lw=0.9)
    pdf.rect(rx, y, rw, 24, fill=BLUE)
    pdf.draw_text("MODULE 4 : ANALYSES BIOLOGIQUES", rx + rw / 2, y + 16, size=8.5, color=WHITE, font='F2', align='center')
    sx4 = rx + (rw - w_scr4) / 2
    pdf.rounded_rect(sx4 - 3, y + 32, w_scr4 + 6, h_scr + 6, r=8, fill=NAVY)
    pdf.draw_image('scr4', sx4, y + 35, w_scr4, h_scr)
    pdf.draw_paragraph(
        "5 sous-modules complets : NFS Complète, Fonction Rénale (Cockcroft-Gault), Bilan Hépatique, Hémostase & Inflammation (CRP/INR/D-Dimères) et Score de Glasgow.",
        rx + 10, y + 278, rw - 20, size=7.6, leading=10.8, color=TEXT_BODY, font='F1'
    )

    by = y + 350
    pdf.draw_text("SPÉCIFICATIONS DÉTAILLÉES DES MOTEURS D'INTERPRÉTATION", 36, by, size=9.5, color=NAVY, font='F2')

    engines = [
        ("A. HÉMATOLOGIE : NFS COMPLÈTE EN 6 VOLETS",
         "Calcul automatique du VGM et de la CCMH. Identification des anémies (microcytaire, normocytaire, macrocytaire ; régénérative vs arégénérative), bicytopénies, pancytopénies, hyperleucocytoses et alerte agranulocytose (PNN < 0.5 G/L).",
         RED_PALE, RED),
        ("B. NÉPHROLOGIE : COCKCROFT-GAULT & STADES KDIGO",
         "Estimation précise de la clairance de la créatinine selon l'âge, le poids, le sexe (K=1.23 ou 1.04) et la créatininémie. Classement automatique en 5 stades d'insuffisance rénale avec recommandations d'adaptation posologique.",
         BLUE_PALE, BLUE),
        ("C. HÉPATOLOGIE & PANCRÉAS : CYTOLYSE VS CHOLESTASE",
         "Analyse des transaminases (ALAT/ASAT + ratio de De Ritis), phosphatases alcalines, GGT, bilirubine totale et lipasémie (alerte pancréatite aiguë immédiate si Lipase > 3N).",
         ORANGE_PALE, ORANGE),
        ("D. HÉMOSTASE, INFLAMMATION & E. SCORE DE GLASGOW (GCS)",
         "Interprétation de la CRP (seuil sepsis), du TP/INR, des D-Dimères (VPN > 98% sous 500 ng/mL) et calcul du Score de Coma de Glasgow (Yeux + Verbe + Motricité) avec alerte d'intubation trachéale urgente si GCS <= 8.",
         GREEN_PALE, GREEN),
    ]
    ey = by + 12
    for etitle, edesc, ebg, ecol in engines:
        pdf.rounded_rect(36, ey, PAGE_W - 72, 70, r=8, fill=ebg, stroke=ecol, lw=0.8)
        pdf.draw_text(etitle, 48, ey + 17, size=8.4, color=NAVY, font='F2')
        pdf.draw_paragraph(edesc, 48, ey + 31, PAGE_W - 96, size=7.8, leading=11.0, color=TEXT_BODY, font='F1')
        ey += 77

    # =========================================================================
    # PAGE 6 : MODULE 5 (SÉMIOLOGIE 29 FICHES & 90 QCM) + CAPTURE
    # =========================================================================
    pdf.new_page()
    draw_header_footer(pdf, 6, TOTAL_PAGES, "Module 5 : Sémiologie & 90 QCM")

    y = section_heading(pdf, "05", "Module Sémiologie & Entraînement (QCM)", "Encyclopédie en 29 fiches cliniques et banque interactive de 90 questions", 36, 62)

    h5 = 304
    w5 = h5 * (360 / 675)
    pdf.rounded_rect(36, y, w5 + 16, h5 + 32, r=12, fill=NAVY, stroke=BLUE, lw=1.2)
    pdf.draw_image('scr5', 44, y + 10, w5, h5)
    pdf.draw_text("Capture réelle : Sémiologie", 44 + w5 / 2, y + h5 + 24, size=7.5, color=BLUE_LIGHT, font='F2', align='center')

    rx = 36 + w5 + 28
    rw = PAGE_W - 36 - rx
    pdf.rounded_rect(rx, y, rw, 168, r=10, fill=BLUE_PALE, stroke=BLUE, lw=1.0)
    pdf.draw_text("8 APPAREILS MÉDICAUX COUVERTS", rx + 12, y + 18, size=8.8, color=NAVY, font='F2')
    apps_list = [
        ("Cardiovasculaire :", "Angor, dyspnée NYHA, syncope... (20 QCM)"),
        ("Respiratoire :", "Hémoptysie, toux, condensation... (10 QCM)"),
        ("Neurologie :", "Syndrome pyramidal, méningé, AVC... (10 QCM)"),
        ("Digestif :", "Méléna, hématémèse, ictère, ascite... (10 QCM)"),
        ("Néphro-Urinaire :", "Hématurie, colique néphrétique... (10 QCM)"),
        ("Locomoteur & Dermato :", "Arthrite vs arthrose, lésions... (20 QCM)"),
        ("Signes Généraux :", "Fièvre prolongée, AEG, choc... (10 QCM)"),
    ]
    ay = y + 34
    for a_t, a_d in apps_list:
        ay = bullet_item(pdf, rx + 10, ay, rw - 20, a_t, a_d, dot_color=BLUE, size=7.4, leading=10.3)

    pdf.rounded_rect(rx, y + 178, rw, 158, r=10, fill=GREEN_PALE, stroke=GREEN, lw=1.0)
    pdf.draw_text("BANQUE INTERACTIVE DE 90 QCM OFFICIELS", rx + 12, y + 196, size=8.8, color=GREEN_DARK, font='F2')
    qy = y + 214
    qy = bullet_item(pdf, rx + 10, qy, rw - 20, "4 Choix (A, B, C, D) :", "Sélection tactile immédiate par appareil.", dot_color=GREEN, size=7.6, leading=10.8)
    qy = bullet_item(pdf, rx + 10, qy, rw - 20, "Correction Immédiate :", "Mise en évidence en vert/rouge et justification des réponses.", dot_color=GREEN, size=7.6, leading=10.8)
    qy = bullet_item(pdf, rx + 10, qy, rw - 20, "Score en Direct :", "Suivi de progression automatique (X / 90) sans serveur.", dot_color=GREEN, size=7.6, leading=10.8)

    sy = y + 354
    pdf.rounded_rect(36, sy, PAGE_W - 72, 290, r=10, fill=WHITE, stroke=BORDER, lw=1.0)
    pdf.rect(36, sy, PAGE_W - 72, 26, fill=NAVY)
    pdf.draw_text("STRUCTURE PÉDAGOGIQUE DES 29 FICHES SÉMIOLOGIQUES", PAGE_W / 2, sy + 17, size=9.0, color=WHITE, font='F2', align='center')
    pdf.draw_paragraph(
        "Chaque signe ou syndrome clinique de l'encyclopédie EL VITA est structuré selon la grille rigoureuse attendue aux examens de faculté (ECOS / DFGSM-DFASM) :",
        48, sy + 40, PAGE_W - 96, size=8.2, leading=11.6, color=TEXT_BODY, font='F1'
    )

    sections_14 = [
        ("1. Définition sémiologique précise", "8. Signes physiques d'accompagnement"),
        ("2. Mécanisme physiopathologique", "9. Valeur & signification clinique"),
        ("3. Étiologies principales hiérarchisées", "10. Diagnostics différentiels majeurs"),
        ("4. Circonstances de survenue", "11. Examens complémentaires de 1re intention"),
        ("5. Interrogatoire spécifique (Anamnèse)", "12. Signes de gravité / Alerte vitale"),
        ("6. Méthode rigoureuse de recherche au lit", "13. Complications & évolution à surveiller"),
        ("7. Examen physique dirigé", "14. Synthèse & points clés d'examen"),
    ]
    s_y = sy + 72
    half_sw = (PAGE_W - 96) / 2
    for s_left, s_right in sections_14:
        pdf.rounded_rect(48, s_y, half_sw - 8, 24, r=5, fill=BG_LIGHT, stroke=BORDER, lw=0.6)
        pdf.draw_text(s_left, 56, s_y + 15.5, size=7.8, color=NAVY, font='F2')
        pdf.rounded_rect(48 + half_sw + 8, s_y, half_sw - 8, 24, r=5, fill=BG_LIGHT, stroke=BORDER, lw=0.6)
        pdf.draw_text(s_right, 56 + half_sw + 8, s_y + 15.5, size=7.8, color=NAVY, font='F2')
        s_y += 29

    # =========================================================================
    # PAGE 7 : GUIDE D'INSTALLATION (ANDROID .APK & IPHONE SAFARI)
    # =========================================================================
    pdf.new_page()
    draw_header_footer(pdf, 7, TOTAL_PAGES, "Guide d'Installation Utilisateurs")

    y = section_heading(pdf, "06", "Guide Officiel d'Installation & Diffusion", "Procédure complète pour installer EL VITA sur Android (.APK) et iPhone (Lien Safari)", 36, 62)

    py_i = pdf.draw_paragraph(
        "Lorsque vous diffusez le projet EL VITA auprès des étudiants ou d'un jury : envoyez le fichier « EL VITA.apk » aux utilisateurs Android, et le lien officiel GitHub Pages aux utilisateurs iPhone. Voici les étapes exactes pour chaque appareil :",
        36, y + 6, PAGE_W - 72, size=8.6, leading=12.6, color=TEXT_BODY, font='F1'
    )

    gy = py_i + 8
    col_w = (PAGE_W - 72 - 14) / 2

    pdf.rounded_rect(36, gy, col_w, 372, r=12, fill=WHITE, stroke=GREEN, lw=1.4)
    pdf.rect(36, gy, col_w, 34, fill=GREEN_DARK)
    pdf.draw_text("UTILISATEURS ANDROID", 36 + col_w / 2, gy + 15, size=9.8, color=WHITE, font='F2', align='center')
    pdf.draw_text("Installation via le fichier « EL VITA.apk »", 36 + col_w / 2, gy + 28, size=7.6, color=GREEN_PALE, font='F1', align='center')

    android_steps = [
        ("1", "Télécharger le fichier APK",
         "Récupérez le fichier « EL VITA.apk » transmis par WhatsApp, Telegram ou e-mail et appuyez dessus pour lancer l'ouverture."),
        ("2", "Autoriser l'installation sécurisée",
         "Android affiche un message de sécurité (« Application inconnue »). Appuyez sur « Paramètres » puis activez « Autoriser cette source »."),
        ("3", "Installer & Ouvrir l'application",
         "Revenez en arrière, appuyez sur « Installer », patientez 5 secondes, puis cliquez sur « Ouvrir »."),
        ("4", "Prêt pour l'hôpital (100% Hors-ligne)",
         "L'icône bleue EL VITA est créée sur votre téléphone. Aucune connexion internet ni création de compte n'est nécessaire."),
    ]
    sy_a = gy + 50
    for num, st_title, st_desc in android_steps:
        pdf.circle(58, sy_a + 8, 11, fill=GREEN)
        pdf.draw_text(num, 58, sy_a + 12, size=9.0, color=WHITE, font='F2', align='center')
        pdf.draw_text(st_title, 76, sy_a + 11, size=8.6, color=NAVY, font='F2')
        sy_a = pdf.draw_paragraph(st_desc, 76, sy_a + 24, col_w - 50, size=7.8, leading=11.2, color=TEXT_BODY, font='F1')
        sy_a += 14

    ix = 36 + col_w + 14
    pdf.rounded_rect(ix, gy, col_w, 372, r=12, fill=WHITE, stroke=BLUE, lw=1.4)
    pdf.rect(ix, gy, col_w, 34, fill=NAVY)
    pdf.draw_text("UTILISATEURS IPHONE (iOS)", ix + col_w / 2, gy + 15, size=9.8, color=WHITE, font='F2', align='center')
    pdf.draw_text("Installation directe via Safari (Sans App Store)", ix + col_w / 2, gy + 28, size=7.6, color=BLUE_LIGHT, font='F1', align='center')

    pdf.rounded_rect(ix + 12, gy + 44, col_w - 24, 34, r=6, fill=BLUE_PALE, stroke=BLUE, lw=0.9)
    pdf.draw_text("LIEN OFFICIEL À OUVRIR DANS SAFARI :", ix + col_w / 2, gy + 57, size=6.9, color=NAVY, font='F2', align='center')
    pdf.draw_text("https://dossoualbert00-commits.github.io/elvita/", ix + col_w / 2, gy + 70, size=7.2, color=BLUE, font='F2', align='center')
    pdf.add_link(ix + 12, gy + 44, col_w - 24, 34, "https://dossoualbert00-commits.github.io/elvita/")

    ios_steps = [
        ("1", "Ouvrir le lien avec Safari",
         "Important : utilisez le navigateur Safari (pas le navigateur interne de WhatsApp). Cliquez en bas sur l'icône Boussole si besoin."),
        ("2", "Appuyer sur le bouton Partager",
         "En bas de l'écran Safari, appuyez sur le bouton Partager (le carré avec une flèche pointant vers le haut)."),
        ("3", "« Sur l'écran d'accueil »",
         "Faites défiler le menu vers le bas, touchez « Sur l'écran d'accueil », puis appuyez sur « Ajouter » en haut à droite."),
        ("4", "Lancement plein écran hors-ligne",
         "L'icône officielle EL VITA s'ajoute à votre iPhone. Ouvrez-la une fois avec internet : elle fonctionne ensuite 100% hors-ligne !"),
    ]
    sy_i = gy + 92
    for num, st_title, st_desc in ios_steps:
        pdf.circle(ix + 22, sy_i + 8, 11, fill=BLUE)
        pdf.draw_text(num, ix + 22, sy_i + 12, size=9.0, color=WHITE, font='F2', align='center')
        pdf.draw_text(st_title, ix + 40, sy_i + 11, size=8.6, color=NAVY, font='F2')
        sy_i = pdf.draw_paragraph(st_desc, ix + 40, sy_i + 24, col_w - 50, size=7.8, leading=11.2, color=TEXT_BODY, font='F1')
        sy_i += 10

    fy = gy + 388
    pdf.rounded_rect(36, fy, PAGE_W - 72, 236, r=10, fill=BG_LIGHT, stroke=BORDER, lw=1.0)
    pdf.rect(36, fy, PAGE_W - 72, 24, fill=NAVY_MID)
    pdf.draw_text("FOIRE AUX QUESTIONS & DÉPANNAGE RAPIDE", 50, fy + 16, size=8.8, color=WHITE, font='F2')

    faqs = [
        ("Pourquoi ne peut-on pas installer le fichier .apk sur un iPhone ?",
         "Apple bloque par construction les fichiers .apk (réservés au système Android). C'est pourquoi EL VITA dispose d'une version iOS PWA dédiée via le lien Safari qui reproduit à 100% les mêmes fonctionnalités, écrans et contenus."),
        ("L'iPhone garde-t-il les données en cas de manque de connexion à l'hôpital ?",
         "Oui. Grâce au Service Worker embarqué, toutes les fiches, les 90 QCM et les calculateurs restent disponibles sans réseau, et les dossiers patients créés sont enregistrés localement sur le téléphone."),
        ("Comment mettre à jour l'application sur iPhone après une nouvelle version ?",
         "Fermez complètement l'application EL VITA sur l'iPhone puis rouvrez-la avec une connexion internet : le système détecte et applique automatiquement la dernière version."),
        ("La version Android ou iPhone collecte-t-elle des données personnelles ?",
         "Non. Aucun compte obligatoire, aucun traceur publicitaire et aucune transmission cloud des données de santé."),
    ]
    qy = fy + 38
    for fq, fa in faqs:
        pdf.draw_text("Q : " + fq, 48, qy, size=8.0, color=BLUE, font='F2')
        qy = pdf.draw_paragraph("R : " + fa, 48, qy + 11, PAGE_W - 96, size=7.6, leading=10.6, color=TEXT_BODY, font='F1')
        qy += 7

    # =========================================================================
    # PAGE 8 : DÉONTOLOGIE, CONFIDENTIALITÉ, IMPACT & CONTACT OFFICIEL
    # =========================================================================
    pdf.new_page()
    draw_header_footer(pdf, 8, TOTAL_PAGES, "Déontologie, Perspectives & Contact")

    y = section_heading(pdf, "07", "Déontologie Médicale, Perspectives & Contact", "Cadre d'utilisation responsable, feuille de route et coordination officielle", 36, 62)

    pdf.rounded_rect(36, y + 6, PAGE_W - 72, 74, r=10, fill=GREEN_PALE, stroke=GREEN_DARK, lw=1.4)
    pdf.draw_text("AVERTISSEMENT DÉONTOLOGIQUE OFFICIEL", 50, y + 23, size=8.8, color=GREEN_DARK, font='F2')
    pdf.draw_paragraph(
        "« El Vita est un outil d'aide à l'observation et au raisonnement clinique. Il ne remplace ni le jugement médical ni l'enseignement. »\n"
        "Les informations, scores et fiches présentés visent exclusivement la formation des étudiants en médecine et doivent toujours être validés sous la supervision d'un médecin senior en situation réelle de soins.",
        50, y + 37, PAGE_W - 100, size=8.0, leading=11.6, color=TEXT_DARK, font='F2'
    )

    cy = y + 94
    pdf.rounded_rect(36, cy, col_w, 184, r=10, fill=WHITE, stroke=BORDER, lw=1.0)
    pdf.rect(36, cy, col_w, 26, fill=NAVY)
    pdf.draw_text("SECRET MÉDICAL & SÉCURITÉ", 36 + col_w / 2, cy + 17, size=8.6, color=WHITE, font='F2', align='center')
    sy1 = cy + 42
    sy1 = bullet_item(pdf, 46, sy1, col_w - 20, "Zéro serveur distant :", "Aucune base cloud ne reçoit les saisies.", dot_color=NAVY, size=7.8, leading=11.2)
    sy1 = bullet_item(pdf, 46, sy1, col_w - 20, "Isolation OS :", "Stockage cloisonné dans la mémoire locale.", dot_color=NAVY, size=7.8, leading=11.2)
    sy1 = bullet_item(pdf, 46, sy1, col_w - 20, "Anonymisation :", "Usage d'alias ou initiales recommandé.", dot_color=NAVY, size=7.8, leading=11.2)
    sy1 = bullet_item(pdf, 46, sy1, col_w - 20, "Éthique stricte :", "Zéro pistage, zéro régie publicitaire.", dot_color=NAVY, size=7.8, leading=11.2)

    pdf.rounded_rect(ix, cy, col_w, 184, r=10, fill=WHITE, stroke=BORDER, lw=1.0)
    pdf.rect(ix, cy, col_w, 26, fill=BLUE)
    pdf.draw_text("PERSPECTIVES & FEUILLE DE ROUTE", ix + col_w / 2, cy + 17, size=8.6, color=WHITE, font='F2', align='center')
    sy2 = cy + 42
    sy2 = bullet_item(pdf, ix + 10, sy2, col_w - 20, "Extensions :", "Pédiatrie, gynéco-obstétrique & infectiologie.", dot_color=BLUE, size=7.8, leading=11.2)
    sy2 = bullet_item(pdf, ix + 10, sy2, col_w - 20, "Préparation ECOS :", "Scénarios chronométrés d'examens cliniques.", dot_color=BLUE, size=7.8, leading=11.2)
    sy2 = bullet_item(pdf, ix + 10, sy2, col_w - 20, "Partenariats :", "Déploiement pilote auprès des CHU et facultés.", dot_color=BLUE, size=7.8, leading=11.2)
    sy2 = bullet_item(pdf, ix + 10, sy2, col_w - 20, "Évolution :", "Mises à jour guidées par les retours d'externes.", dot_color=BLUE, size=7.8, leading=11.2)

    py = cy + 198
    pdf.rounded_rect(36, py, PAGE_W - 72, 168, r=10, fill=BLUE_PALE, stroke=BLUE, lw=1.2)
    pdf.draw_text("DISCOURS DE PRÉSENTATION SYNTHÉTIQUE (PITCH ORAL)", 50, py + 19, size=9.2, color=NAVY, font='F2')
    pdf.draw_paragraph(
        "« Mesdames et Messieurs, chers Maîtres et confrères, je vous présente EL VITA : une plateforme numérique médicale indépendante que j'ai conçue pour répondre à un besoin concret de tout étudiant en médecine au lit du malade.\n\n"
        "Trop souvent en stage hospitalier ou en garde de nuit, l'externe manque de connexion internet, doit consulter plusieurs ouvrages dispersés et n'a pas de cadre sécurisé pour s'exercer au raisonnement clinique. EL VITA réunit dans la poche, 100% hors-ligne et sans aucune transmission de données patient : la formation méthodique à l'interrogatoire en 14 étapes, le suivi des constantes vitales, 5 moteurs d'analyses biologiques, 6 fiches de vigilance épidémique, 29 fiches sémiologiques complètes et 90 QCM d'entraînement.\n\n"
        "Accessible gratuitement aussi bien sur Android que sur iPhone, EL VITA porte une ambition simple : rendre l'apprentissage clinique plus structuré, rigoureux et accessible à chaque futur médecin. »",
        50, py + 35, PAGE_W - 100, size=8.0, leading=11.5, color=TEXT_DARK, font='F3'
    )

    fy = py + 182
    pdf.rounded_rect(36, fy, PAGE_W - 72, 150, r=12, fill=NAVY, stroke=BLUE_LIGHT, lw=1.5)
    pdf.draw_image('founder', 52, fy + 15, 80, 120)
    pdf.draw_text("CONTACT OFFICIEL & PROPRIÉTÉ INTELLECTUELLE", 150, fy + 28, size=8.2, color=BLUE_LIGHT, font='F2')
    pdf.draw_text("ALBERT DOSSOU", 150, fy + 50, size=17, color=WHITE, font='F2')
    pdf.draw_text("Fondateur & Propriétaire d'EL VITA", 150, fy + 68, size=10.0, color=(186/255, 230/255, 253/255), font='F2')
    pdf.line(150, fy + 80, PAGE_W - 56, fy + 80, stroke=(30/255, 58/255, 138/255), lw=0.8)
    pdf.draw_text("Courriel officiel :  dossoualbert00@gmail.com", 150, fy + 97, size=9.0, color=WHITE, font='F1')
    pdf.draw_text("Lien iOS / Web   :  https://dossoualbert00-commits.github.io/elvita/", 150, fy + 114, size=9.0, color=BLUE_LIGHT, font='F1')
    pdf.draw_text("Copyright © EL VITA — Tous droits réservés. Plateforme pédagogique gratuite.", 150, fy + 133, size=7.8, color=TEXT_MUTED, font='F1')
    pdf.add_link(150, fy + 87, 300, 14, "mailto:dossoualbert00@gmail.com")
    pdf.add_link(150, fy + 104, 380, 14, "https://dossoualbert00-commits.github.io/elvita/")

    pdf.save("docs/EL_VITA_Dossier_Presentation.pdf")


if __name__ == "__main__":
    build_dossier()
