// EL VITA Service Worker - Network-first for navigation, Cache-first for assets with offline support
const CACHE_NAME = 'elvita-cache-v5';
const ASSETS_TO_CACHE = [
  './',
  './index.html',
  './styles.css',
  './medical_data.js',
  './app.js',
  './manifest.json',
  './assets/logo.png',
  './assets/banner.jpg',
  './assets/founder.jpg'
];

self.addEventListener('install', (event) => {
  self.skipWaiting();
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      return cache.addAll(ASSETS_TO_CACHE);
    })
  );
});

self.addEventListener('activate', (event) => {
  event.waitUntil(
    caches.keys().then((keys) => {
      return Promise.all(
        keys.map((key) => {
          if (key !== CACHE_NAME) {
            return caches.delete(key);
          }
        })
      );
    }).then(() => self.clients.claim())
  );
});

self.addEventListener('fetch', (event) => {
  const req = event.request;
  
  // Pour la page HTML principale (navigation) : Réseau d'abord pour avoir la dernière version, puis cache
  if (req.mode === 'navigate') {
    event.respondWith(
      fetch(req)
        .then((networkResponse) => {
          if (networkResponse && networkResponse.status === 200) {
            const responseToCache = networkResponse.clone();
            caches.open(CACHE_NAME).then((cache) => cache.put(req, responseToCache));
          }
          return networkResponse;
        })
        .catch(() => {
          return caches.match('./index.html') || caches.match('./');
        })
    );
    return;
  }

  // Pour les autres ressources (CSS, JS, images) : Cache d'abord, sinon réseau
  event.respondWith(
    caches.match(req).then((cachedResponse) => {
      if (cachedResponse) {
        // En arrière-plan, rafraîchir le cache
        fetch(req).then((networkResponse) => {
          if (networkResponse && networkResponse.status === 200) {
            caches.open(CACHE_NAME).then((cache) => cache.put(req, networkResponse));
          }
        }).catch(() => {});
        return cachedResponse;
      }
      return fetch(req).then((networkResponse) => {
        if (networkResponse && networkResponse.status === 200) {
          const responseToCache = networkResponse.clone();
          caches.open(CACHE_NAME).then((cache) => cache.put(req, responseToCache));
        }
        return networkResponse;
      });
    })
  );
});
