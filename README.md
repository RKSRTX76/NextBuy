<div align="center">

# 🛍️ NextBuy

### A modern Android eCommerce app built with Kotlin & Jetpack Compose

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)

</div>

---

## 📱 Overview

**NextBuy** is a fully functional Android eCommerce application that offers a seamless shopping experience. Built with modern Android development practices, it features clean architecture, real-time authentication, a rich product catalogue powered by the DummyJSON API, and a polished UI crafted entirely in Jetpack Compose.

---

## ✨ Features

### 🔐 Authentication
- Email & Password Sign Up / Sign In
- Google Sign-In / Sign-Up via Firebase Auth
- Persistent login sessions
- Secure sign-out

### 👤 User Profile
- View and update profile information (name, email, avatar)
- Profile data stored and synced via Firebase Firestore
- Real-time profile updates

### 🛒 Shopping
- Browse products from the [DummyJSON API](https://dummyjson.com/)
- Product listing with images, titles, prices, and ratings
- Product detail screen with full description, category, and stock info
- Category-based product filtering
- Search functionality

### 🎨 UI / UX
- Built 100% with Jetpack Compose
- Material 3 design system
- Responsive layouts
- Smooth navigation with Compose Navigation

---

## 🏗️ Architecture

NextBuy follows **Clean Architecture** principles with a clear separation of concerns across three layers:

```
app/
├── data/
│   ├── remote/          # API calls (DummyJSON, Retrofit)
│   ├── repository/      # Repository implementations
│   └── firebase/        # Firebase Auth & Firestore data sources
│
├── domain/
│   ├── model/           # Core domain models
│   ├── repository/      # Repository interfaces
│   └── usecase/         # Business logic use cases
│
└── presentation/
    ├── ui/              # Composable screens & components
    ├── viewmodel/       # ViewModels (StateFlow, LiveData)
    └── navigation/      # Compose Navigation graph
```

### Design Patterns Used
- **MVVM** (Model-View-ViewModel)
- **Repository Pattern**
- **Use Case / Interactor Pattern**
- **Dependency Injection** with Hilt

---

## 🛠️ Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin |
| UI Framework | Jetpack Compose |
| Architecture | Clean Architecture + MVVM |
| Dependency Injection | Hilt |
| Authentication | Firebase Authentication |
| Database / Storage | Firebase Firestore |
| Product API | DummyJSON REST API |
| Networking | Ktor |
| Image Loading | Coil |
| Navigation | Jetpack Compose Navigation |
| Async | Kotlin Coroutines + Flow |

---

## 🔥 Firebase Setup

This project uses Firebase for authentication and user profile storage. To run the project locally:

1. Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project.
2. Register your Android app with your package name.
3. Download the `google-services.json` file and place it in the `app/` directory.
4. Enable the following in Firebase Console:
   - **Authentication** → Email/Password provider
   - **Authentication** → Google provider
   - **Firestore Database**

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or newer
- JDK 17+
- Android SDK 26+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/NextBuy.git
   cd NextBuy
   ```

2. **Add Firebase config**

   Place your `google-services.json` inside the `app/` folder (see Firebase Setup above).

3. **Open in Android Studio**

   Open the project in Android Studio and let Gradle sync complete.

4. **Run the app**

   Connect a device or start an emulator, then click **Run ▶**.

---

## 📡 API Reference

NextBuy uses the free [DummyJSON API](https://dummyjson.com/) for product data.

| Endpoint | Description |
|---|---|
| `GET /products` | Fetch all products |
| `GET /products/{id}` | Fetch a single product |
| `GET /products/search?q=` | Search products |
| `GET /products/categories` | Fetch all categories |
| `GET /products/category/{name}` | Fetch products by category |

---

## 📂 Project Structure

```
NextBuy/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/yourpackage/nextbuy/
│   │       │   ├── data/
│   │       │   ├── domain/
│   │       │   ├── presentation/
│   │       │   └── di/              # Hilt modules
│   │       └── res/
│   └── google-services.json         # ← Add this file
├── build.gradle.kts
└── settings.gradle.kts
```

---

## 🤝 Contributing

Contributions are welcome! If you find a bug or have a feature request, feel free to open an issue or submit a pull request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

```
MIT License

Copyright (c) 2025 Your Name

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
```

---

<div align="center">
  Made with ❤️ using Kotlin & Jetpack Compose
</div>
