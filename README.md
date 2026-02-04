# 📅 Akıllı Ajanda API

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-12+-blue?style=for-the-badge&logo=postgresql)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)
![Gradle](https://img.shields.io/badge/Gradle-7.x-02303A?style=for-the-badge&logo=gradle)

**Modern, güvenli ve ölçeklenebilir görev ve etkinlik yönetim API'si**

[Özellikler](#-özellikler) • [Kurulum](#-kurulum) • [Dokümantasyon](#-api-dokümantasyonu) • [Katkıda Bulunma](#-katkıda-bulunma)

</div>

---

## 📑 İçindekiler

- [Genel Bakış](#-genel-bakış)
- [Özellikler](#-özellikler)
- [Mimari](#-mimari)
- [Teknoloji Stack'i](#-teknoloji-stacki)
- [Gereksinimler](#-gereksinimler)
- [Kurulum](#-kurulum)
  - [Hızlı Başlangıç](#hızlı-başlangıç)
  - [Detaylı Kurulum](#detaylı-kurulum)
- [Yapılandırma](#-yapılandırma)
- [API Dokümantasyonu](#-api-dokümantasyonu)
- [API Kullanım Örnekleri](#-api-kullanım-örnekleri)
- [Güvenlik](#-güvenlik)
- [Test](#-test)
- [Deployment](#-deployment)
- [Sorun Giderme](#-sorun-giderme)
- [Katkıda Bulunma](#-katkıda-bulunma)
- [Lisans](#-lisans)

---

## 🎯 Genel Bakış

Akıllı Ajanda API, kullanıcıların görevlerini ve etkinliklerini verimli bir şekilde yönetmelerini sağlayan, RESTful prensiplere uygun geliştirilmiş bir Spring Boot uygulamasıdır. JWT tabanlı kimlik doğrulama, Firebase push notification desteği ve kapsamlı API dokümantasyonu ile modern web ve mobil uygulamalar için ideal bir backend çözümü sunar.

### Temel Özellikler

- ✅ **RESTful API** - Standart HTTP metodları ile tutarlı API tasarımı
- 🔐 **Güvenli Kimlik Doğrulama** - JWT token tabanlı güvenlik
- 📱 **Push Notification** - Firebase Cloud Messaging entegrasyonu
- 📊 **Kategori Yönetimi** - Görev ve etkinlikleri organize etme
- 🔄 **Tekrarlayan Görevler** - Esnek tekrarlama kuralları
- 📸 **Dosya Yönetimi** - Resim yükleme ve saklama
- 📚 **Otomatik Dokümantasyon** - Swagger/OpenAPI ile interaktif dokümantasyon

---

## ✨ Özellikler

### 🔐 Kimlik Doğrulama ve Yetkilendirme
- Kullanıcı kaydı ve girişi
- JWT token tabanlı kimlik doğrulama
- Güvenli şifre hashleme (BCrypt)
- Token yenileme mekanizması

### ✅ Görev Yönetimi
- CRUD operasyonları (Create, Read, Update, Delete)
- Görev durumu takibi (TODO, IN_PROGRESS, COMPLETED)
- Önem seviyesi belirleme (LOW, MEDIUM, HIGH)
- Kategori bazlı filtreleme
- Tarih aralığı ile sorgulama

### 📅 Etkinlik Yönetimi
- Etkinlik oluşturma ve yönetimi
- Konum bilgisi desteği (latitude/longitude)
- Kategori bazlı organizasyon
- Tarih ve saat yönetimi

### 🏷️ Kategori Yönetimi
- Kullanıcı bazlı kategori oluşturma
- Kategori güncelleme ve silme
- Görev ve etkinliklerle ilişkilendirme

### 🔄 Tekrarlayan Görevler
- Günlük, haftalık, aylık tekrarlama kuralları
- Özel tekrarlama desenleri
- Otomatik görev oluşturma

### 🔔 Bildirim Sistemi
- Firebase Cloud Messaging entegrasyonu
- Görev ve etkinlik bildirimleri
- Zamanlanmış bildirimler
- Push notification desteği

### 📸 Dosya Yönetimi
- Resim yükleme (JPEG, PNG)
- Dosya boyutu validasyonu (max 10MB)
- Güvenli dosya saklama

### ⚙️ Kullanıcı Ayarları
- Kişiselleştirilmiş ayarlar
- Bildirim tercihleri
- Tema ve dil ayarları

---

## 🏗️ Mimari

```
┌─────────────────┐
│   Client Apps   │
│  (Web/Mobile)   │
└────────┬────────┘
         │
         │ HTTP/REST
         │
┌────────▼─────────────────────────────────────────┐
│           Spring Boot Application                │
│  ┌──────────────────────────────────────────┐   │
│  │         Security Layer (JWT)             │   │
│  └──────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────┐   │
│  │         REST Controllers                 │   │
│  │  • AuthController                        │   │
│  │  • TaskController                        │   │
│  │  • EventController                       │   │
│  │  • CategoryController                    │   │
│  └──────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────┐   │
│  │         Service Layer                    │   │
│  │  • Business Logic                        │   │
│  │  • Validation                            │   │
│  └──────────────────────────────────────────┘   │
│  ┌──────────────────────────────────────────┐   │
│  │         Repository Layer (JPA)           │   │
│  └──────────────────────────────────────────┘   │
└────────┬─────────────────────────────────────────┘
         │
         │ JDBC
         │
┌────────▼────────┐      ┌──────────────────┐
│   PostgreSQL    │      │  Firebase Admin  │
│   Database      │      │      SDK         │
└─────────────────┘      └──────────────────┘
```

---

## 🛠️ Teknoloji Stack'i

### Backend Framework
- **Spring Boot 3.5.6** - Ana framework
- **Spring Security** - Güvenlik ve kimlik doğrulama
- **Spring Data JPA** - Veritabanı erişim katmanı
- **Hibernate** - ORM framework

### Veritabanı
- **PostgreSQL 12+** - İlişkisel veritabanı

### Güvenlik
- **JJWT** - JWT token işlemleri
- **BCrypt** - Şifre hashleme

### Bildirim
- **Firebase Admin SDK 9.1.1** - Push notification servisi

### Dokümantasyon
- **SpringDoc OpenAPI 2.7.0** - API dokümantasyonu

### Validasyon
- **Hibernate Validator 8.0.1** - Bean validation

### Build & Test
- **Gradle** - Build automation
- **JUnit 5** - Unit testing
- **JaCoCo** - Code coverage
- **H2 Database** - Test veritabanı

### Diğer
- **Lombok** - Boilerplate kod azaltma
- **Jackson** - JSON işleme

---

## 📋 Gereksinimler

### Sistem Gereksinimleri
- **Java Development Kit (JDK)** 21 veya üzeri
- **PostgreSQL** 12 veya üzeri
- **Gradle** 7.x veya üzeri (veya Gradle Wrapper)
- **Maven Central** erişimi (bağımlılıklar için)

### Geliştirme Ortamı
- **IDE**: IntelliJ IDEA, Eclipse, VS Code (önerilir)
- **Git** - Versiyon kontrolü
- **Postman** veya **cURL** - API testi (opsiyonel)

### Firebase Yapılandırması
- Firebase projesi
- Firebase Admin SDK service account JSON dosyası

---

## 🚀 Kurulum

### Hızlı Başlangıç

```bash
# Repository'yi klonlayın
git clone https://github.com/seyitkarahan/akilli-ajanda-api.git
cd akilli-ajanda-api

# Veritabanını oluşturun (PostgreSQL)
createdb akilli_ajanda_db

# Uygulamayı çalıştırın
./gradlew bootRun
```

Uygulama `http://localhost:8082` adresinde çalışacaktır.

### Detaylı Kurulum

#### 1. Projeyi Klonlayın

```bash
git clone https://github.com/seyitkarahan/akilli-ajanda-api.git
cd akilli-ajanda-api
```

#### 2. PostgreSQL Veritabanı Kurulumu

```bash
# PostgreSQL'e bağlanın
psql -U postgres

# Veritabanı oluşturun
CREATE DATABASE akilli_ajanda_db;

# Kullanıcı oluşturun (opsiyonel)
CREATE USER ajanda_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE akilli_ajanda_db TO ajanda_user;
```

#### 3. Uygulama Yapılandırması

`src/main/resources/application.properties` dosyasını düzenleyin:

```properties
# Veritabanı yapılandırması
spring.datasource.url=jdbc:postgresql://localhost:5432/akilli_ajanda_db
spring.datasource.username=postgres
spring.datasource.password=your_password

# JWT yapılandırması
jwt.secret=your-secret-key-min-256-bits
jwt.expiration=86400000

# Dosya yükleme yapılandırması
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
file.upload-dir=/path/to/upload/directory
```

#### 4. Firebase Yapılandırması

1. [Firebase Console](https://console.firebase.google.com/) üzerinden proje oluşturun
2. Service Account Key oluşturun
3. JSON dosyasını proje root dizinine `firebase-service-account.json` olarak kaydedin
4. `FirebaseConfig.java` dosyasında path'i kontrol edin

#### 5. Uygulamayı Çalıştırın

**Gradle Wrapper ile:**
```bash
./gradlew bootRun
```

**Gradle ile:**
```bash
gradle bootRun
```

**JAR dosyası olarak:**
```bash
./gradlew build
java -jar build/libs/akilli-ajanda-api-0.0.1-SNAPSHOT.jar
```

---

## ⚙️ Yapılandırma

### Environment Variables

Aşağıdaki environment variable'ları kullanarak uygulamayı yapılandırabilirsiniz:

| Variable | Açıklama | Varsayılan |
|----------|----------|------------|
| `SPRING_DATASOURCE_URL` | PostgreSQL bağlantı URL'i | `jdbc:postgresql://localhost:5432/akilli_ajanda_db` |
| `SPRING_DATASOURCE_USERNAME` | Veritabanı kullanıcı adı | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | Veritabanı şifresi | - |
| `JWT_SECRET` | JWT imzalama anahtarı | - |
| `JWT_EXPIRATION` | Token geçerlilik süresi (ms) | `86400000` |
| `SERVER_PORT` | Uygulama portu | `8082` |
| `FILE_UPLOAD_DIR` | Dosya yükleme dizini | `/Users/seyitkarahan/uploads/` |

### Application Properties

Detaylı yapılandırma için `application.properties` dosyasını inceleyin:

```properties
# Server yapılandırması
server.port=8082

# JPA yapılandırması
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Logging
logging.level.com.seyitkarahan=DEBUG
```

---

## 📖 API Dokümantasyonu

Uygulama çalıştıktan sonra Swagger UI'ya erişebilirsiniz:

- **Swagger UI**: http://localhost:8082/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8082/v3/api-docs

Swagger UI üzerinden tüm endpoint'leri test edebilir ve API dokümantasyonunu inceleyebilirsiniz.

---

## 💡 API Kullanım Örnekleri

### 1. Kullanıcı Kaydı

```bash
curl -X POST http://localhost:8082/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securePassword123"
  }'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
```

### 2. Kullanıcı Girişi

```bash
curl -X POST http://localhost:8082/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "securePassword123"
  }'
```

### 3. Görev Oluşturma

```bash
curl -X POST http://localhost:8082/api/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "title": "Proje sunumu hazırla",
    "description": "Yarınki toplantı için sunum hazırla",
    "status": "TODO",
    "startTime": "2026-02-05T09:00:00",
    "endTime": "2026-02-05T17:00:00",
    "importanceLevel": "HIGH",
    "categoryId": 1
  }'
```

**Response:**
```json
{
  "id": 1,
  "title": "Proje sunumu hazırla",
  "description": "Yarınki toplantı için sunum hazırla",
  "status": "TODO",
  "startTime": "2026-02-05T09:00:00",
  "endTime": "2026-02-05T17:00:00",
  "importanceLevel": "HIGH",
  "category": {
    "id": 1,
    "name": "İş"
  }
}
```

### 4. Görevleri Listeleme

```bash
# Tüm görevler
curl -X GET http://localhost:8082/api/tasks \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"

# Kategoriye göre filtreleme
curl -X GET "http://localhost:8082/api/tasks?categoryId=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 5. Etkinlik Oluşturma

```bash
curl -X POST http://localhost:8082/api/events \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "title": "Takım Toplantısı",
    "description": "Haftalık takım toplantısı",
    "startTime": "2026-02-05T14:00:00",
    "endTime": "2026-02-05T15:30:00",
    "location": "Konferans Salonu A",
    "latitude": 41.0082,
    "longitude": 28.9784,
    "categoryId": 1
  }'
```

### 6. Kategori Oluşturma

```bash
curl -X POST http://localhost:8082/api/categories \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Kişisel"
  }'
```

---

## 🔒 Güvenlik

### Kimlik Doğrulama

API, JWT (JSON Web Token) tabanlı stateless kimlik doğrulama kullanır. Çoğu endpoint için authentication gereklidir.

**Token Kullanımı:**
```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Public Endpoints

Aşağıdaki endpoint'ler authentication gerektirmez:
- `POST /api/auth/register` - Kullanıcı kaydı
- `POST /api/auth/login` - Kullanıcı girişi
- `GET /swagger-ui/**` - API dokümantasyonu
- `GET /v3/api-docs/**` - OpenAPI spesifikasyonu

### Güvenlik Özellikleri

- ✅ BCrypt ile şifre hashleme
- ✅ JWT token tabanlı kimlik doğrulama
- ✅ CORS yapılandırması
- ✅ SQL injection koruması (JPA/Hibernate)
- ✅ XSS koruması
- ✅ Dosya yükleme validasyonu
- ✅ Rate limiting (yapılandırılabilir)

### Güvenlik En İyi Uygulamaları

1. **Production ortamında** güçlü bir `JWT_SECRET` kullanın (minimum 256 bit)
2. Token expiration süresini makul bir değerde tutun
3. HTTPS kullanın
4. Şifreler için güçlü politika uygulayın
5. Rate limiting ekleyin (ör: Spring Cloud Gateway)

---

## 🧪 Test

### Testleri Çalıştırma

```bash
# Tüm testleri çalıştır
./gradlew test

# Belirli bir test sınıfını çalıştır
./gradlew test --tests "com.seyitkarahan.akilli_ajanda_api.service.TaskServiceTest"

# Testleri verbose modda çalıştır
./gradlew test --info
```

### Kod Kapsamı Raporu

```bash
# JaCoCo test raporu oluştur
./gradlew jacocoTestReport

# Raporu görüntüle
open build/reports/jacoco/test/html/index.html
```

### Test Yapısı

```
src/test/java/
├── integration/          # Integration testler
│   ├── AuthIntegrationTest.java
│   ├── TaskControllerIntegrationTest.java
│   └── ...
└── service/             # Unit testler
    ├── TaskServiceTest.java
    ├── AuthServiceTest.java
    └── ...
```

### Test Kapsamı

- ✅ Unit testler (Service layer)
- ✅ Integration testler (Controller layer)
- ✅ Repository testleri
- ✅ Security testleri

---

## 🚢 Deployment

### JAR Oluşturma

```bash
# Production build
./gradlew clean build -x test

# Test ile build
./gradlew clean build
```

Oluşturulan JAR dosyası: `build/libs/akilli-ajanda-api-0.0.1-SNAPSHOT.jar`

### Docker ile Çalıştırma

```dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/akilli-ajanda-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```bash
docker build -t akilli-ajanda-api .
docker run -p 8082:8082 akilli-ajanda-api
```

### Production Checklist

- [ ] Environment variables yapılandırıldı
- [ ] Güçlü JWT secret kullanılıyor
- [ ] HTTPS aktif
- [ ] Veritabanı backup stratejisi hazır
- [ ] Logging yapılandırıldı
- [ ] Monitoring ve alerting kuruldu
- [ ] Rate limiting aktif
- [ ] CORS yapılandırması production için ayarlandı

---

## 🔧 Sorun Giderme

### Yaygın Sorunlar

#### 1. Veritabanı Bağlantı Hatası

**Sorun:** `Connection refused` veya `Authentication failed`

**Çözüm:**
```bash
# PostgreSQL servisinin çalıştığını kontrol edin
sudo systemctl status postgresql

# Veritabanı kullanıcı bilgilerini kontrol edin
psql -U postgres -l
```

#### 2. Port Zaten Kullanımda

**Sorun:** `Port 8082 is already in use`

**Çözüm:**
```bash
# Portu kullanan process'i bulun
lsof -i :8082

# Process'i sonlandırın veya application.properties'te portu değiştirin
server.port=8083
```

#### 3. JWT Token Geçersiz

**Sorun:** `Invalid JWT token` hatası

**Çözüm:**
- Token'ın süresinin dolmadığından emin olun
- `Authorization` header'ının doğru formatta olduğunu kontrol edin: `Bearer <token>`
- JWT secret'ın doğru yapılandırıldığından emin olun

#### 4. Firebase Bağlantı Hatası

**Sorun:** `Firebase initialization error`

**Çözüm:**
- `firebase-service-account.json` dosyasının doğru konumda olduğunu kontrol edin
- Firebase proje ayarlarını kontrol edin
- Service account key'in geçerli olduğundan emin olun

#### 5. Dosya Yükleme Hatası

**Sorun:** `File size exceeds maximum`

**Çözüm:**
- Dosya boyutunun 10MB'dan küçük olduğundan emin olun
- `file.upload-dir` dizininin yazılabilir olduğunu kontrol edin

### Log Seviyesini Artırma

```properties
# application.properties
logging.level.com.seyitkarahan=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

---

## 🤝 Katkıda Bulunma

Katkılarınızı bekliyoruz! Projeye katkıda bulunmak için:

### Katkı Süreci

1. **Repository'yi Fork Edin**
   ```bash
   git clone https://github.com/seyitkarahan/akilli-ajanda-api.git
   ```

2. **Feature Branch Oluşturun**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Değişikliklerinizi Commit Edin**
   ```bash
   git commit -m 'feat: Add amazing feature'
   ```

4. **Branch'inizi Push Edin**
   ```bash
   git push origin feature/amazing-feature
   ```

5. **Pull Request Oluşturun**

### Commit Mesajı Kuralları

- `feat:` Yeni özellik
- `fix:` Hata düzeltmesi
- `docs:` Dokümantasyon değişiklikleri
- `style:` Kod formatı (kod davranışını değiştirmez)
- `refactor:` Kod refaktörü
- `test:` Test ekleme veya düzeltme
- `chore:` Build süreci veya yardımcı araçlar

### Kod Standartları

- Java kod stillerine uyun
- Unit testler yazın
- Javadoc ekleyin
- Linter uyarılarını düzeltin

---

## 📝 Lisans

Bu proje [MIT Lisansı](LICENSE) altında lisanslanmıştır.

```
MIT License

Copyright (c) 2026 Seyit Karahan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 👥 Geliştirici

**Seyit Karahan**

- GitHub: [@seyitkarahan](https://github.com/seyitkarahan)
- Email: [İletişim için GitHub üzerinden ulaşabilirsiniz]

---

## 🙏 Teşekkürler

Bu projenin geliştirilmesinde kullanılan tüm açık kaynak kütüphanelere ve topluluğa teşekkürler!

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
- [Firebase](https://firebase.google.com/)
- Ve diğer tüm bağımlılıklar...

---

<div align="center">

**⭐ Bu projeyi beğendiyseniz yıldız vermeyi unutmayın! ⭐**

Made with ❤️ by [Seyit Karahan](https://github.com/seyitkarahan)

</div>
