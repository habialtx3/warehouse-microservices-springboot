# ==========================================
# Tahap 1: Build (Kompilasi kode otomatis)
# ==========================================
# Gunakan JDK standar, bukan image Gradle
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# Salin seluruh kode source aplikasi ke dalam container
COPY . .

# Berikan izin eksekusi pada file gradlew (penting karena kamu pakai Windows)
RUN chmod +x ./gradlew

# Jalankan proses build menggunakan wrapper bawaan project
RUN ./gradlew clean build -x test

# ==========================================
# Tahap 2: Run (Menjalankan aplikasi)
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Hanya ambil file .jar hasil build dari Tahap 1
COPY --from=builder /app/build/libs/*.jar app.jar

# Jalankan aplikasinya
ENTRYPOINT ["java", "-jar", "app.jar"]