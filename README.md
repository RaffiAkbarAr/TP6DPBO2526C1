# TP6 DPBO 2025/2026 — Flappy Bird

# janji 

Saya Raffi Abar ardiansyah dengan NIM 2511604 mengerjakan tugas tp6  pembuatan game Flappy Bird menggunakan Java Swing untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

## 1. Deskripsi Program

Flappy Bird merupakan permainan sederhana yang dibuat menggunakan bahasa pemrograman Java dengan GUI (Graphical User Interface) menggunakan Java Swing.

Pemain mengendalikan burung agar dapat melewati rintangan berupa pipa tanpa menabraknya atau jatuh ke batas bawah jendela permainan. Setiap kali burung berhasil melewati pipa, skor akan bertambah satu poin.

Program ini dibuat untuk memenuhi Tugas Praktikum 6 mata kuliah Desain dan Pemrograman Berorientasi Objek (DPBO), berdasarkan materi Java Swing GUI: Gamedev Part 2.

## 2. Desain Program

Program dibagi menjadi beberapa class dengan tanggung jawab yang berbeda.

### A. Main.java

Class `Main` merupakan titik awal program. Class ini menjalankan aplikasi dan menampilkan jendela permainan menggunakan JFrame.

### B. GamePanel.java

Class `GamePanel` merupakan bagian utama yang mengatur jalannya permainan. Class ini menangani tampilan permainan, pergerakan objek, input pemain, skor, kondisi Game Over, dan restart permainan.

Class ini juga menggunakan Timer untuk menjalankan pembaruan permainan secara berkala serta menggambar objek melalui mekanisme rendering pada JPanel.

### C. Player.java

Class `Player` merepresentasikan burung yang dikendalikan pemain. Class ini mengatur posisi, pergerakan, dan aksi burung ketika pemain memberikan input.

### D. Pipe.java

Class `Pipe` merepresentasikan rintangan berupa pipa. Objek pipa bergerak dari kanan ke kiri sehingga pemain harus mengendalikan burung agar dapat melewati celah di antara pipa.

### E. AudioManager.java

Class `AudioManager` mengatur pemutaran audio dalam permainan, termasuk musik latar atau Background Music (BGM) dan efek suara ketika burung melompat.

### F. Hubungan Antarclass

- `Main` menjalankan aplikasi dan menampilkan jendela permainan.
- `GamePanel` mengatur logika permainan dan menampilkan objek.
- `GamePanel` menggunakan objek `Player` untuk mengendalikan burung.
- `GamePanel` mengelola objek `Pipe` sebagai rintangan.
- `GamePanel` menggunakan `AudioManager` untuk memainkan audio.

## 3. Struktur Folder

```
│   README.md
│
├───.idea
│       .gitignore
│       .name
│       misc.xml
│       modules.xml
│       workspace.xml
│
├───Dokumentasi
│       game over .png
│       game play.png
│       Menu Awal.png
│
└───src
    │   AudioManager.class
    │   AudioManager.java
    │   GamePanel.class
    │   GamePanel.java
    │   Main$MenuPanel.class
    │   Main.class
    │   Main.java
    │   Pipe.class
    │   Pipe.java
    │   Player.class
    │   Player.java
    │
    ├───assets
    │       a.png
    │       bgm.wav
    │       bird.png
    │       jump.wav
    │
    └───data
            best_score.txt
```
## 4. Alur Permainan

Alur permainan Flappy Bird adalah sebagai berikut:

1. Program dijalankan dan menampilkan Main Menu.
2. Pemain memilih tombol untuk memulai permainan.
3. Burung mulai dikendalikan oleh pemain menggunakan keyboard atau mouse.
4. Pipa muncul dan bergerak dari kanan ke kiri sebagai rintangan.
5. Pemain berusaha melewati celah di antara pipa.
6. Setiap kali burung berhasil melewati pipa, skor bertambah satu poin.
7. Jika burung menabrak pipa atau jatuh ke batas bawah jendela permainan, permainan berakhir dan layar Game Over ditampilkan.
8. Layar Game Over menampilkan skor akhir dan Best Score.
9. Pemain dapat menekan tombol `R` untuk memulai ulang permainan.
10. Best Score disimpan agar dapat digunakan kembali ketika permainan dibuka lagi.

## 5. Kontrol Permainan

| Input | Fungsi |
|---|---|
| SPACE | Membuat burung melompat |
| Panah Atas | Membuat burung melompat |
| Klik mouse | Membuat burung melompat |
| R | Memulai ulang permainan setelah Game Over |
| Tombol Start | Memulai permainan dari Main Menu |
| Tombol Exit | Keluar dari permainan |

## 6. Dokumentasi Program

Berikut dokumentasi visual dari permainan yang dibuat:

### a. Tampilan Menu Awal

![Menu Awal](Dokumentasi/Menu%20Awal.png)

Menu awal menampilkan judul permainan, nama edisi Nusantara, serta tombol `MULAI BERMAIN` dan `KELUAR`.

### b. Tampilan Saat Bermain

![Gameplay](Dokumentasi/game%20play.png)

Pada layar permainan, pemain akan melihat burung, pipa, skor, dan latar belakang yang bergerak. Pemain harus menghindari tabrakan agar dapat terus bermain.

### c. Tampilan Game Over

![Game Over](Dokumentasi/game%20over%20.png)

Ketika burung menabrak pipa atau jatuh, permainan akan berhenti dan menampilkan tampilan Game Over beserta skor akhir dan best score.

### d. Tampilan Restart

![Restart](Dokumentasi/restart%20.png)

Setelah game over, pemain dapat melakukan restart untuk memulai sesi permainan baru dan melanjutkan tantangan.

### e. Video Gameplay

[Video Gameplay](Dokumentasi/video%20gameplay.mp4)

