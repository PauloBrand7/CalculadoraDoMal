# Calculator App (Android)

This is a native Android calculator application originally developed as a technical challenge during an internship at Invillia.

The application performs standard arithmetic operations and includes a complete history log, storing all previous calculations locally.

---

## Features

* **Standard Calculations:** Perform addition, subtraction, multiplication, division, and percentage operations.
* **Calculation History:** All operations are automatically saved to a local database.
* **History Screen:** A dedicated "Results" screen displays a complete log of past calculations, including the date and time each was performed.
* **Persistent Storage:** User data and history are stored locally using Room DB.
* **Decimal Support:** The calculator correctly handles floating-point numbers.
* **Clean UI:** Designed with a clean, no-toolbar interface for a focused user experience.
* **About Screen:** Contains developer credits and project information.
* **Contact Screen:** A contact option that redirects the user to their device's default email client.

## Tech Stack & Architecture

* **Language:** Kotlin
* **Architecture:** Clean Architecture
* **Local Storage:** Room DB
* **Image Loading:** Glide
* **UI:** XML Layouts

## Getting Started

To clone and run this project locally, you will need Android Studio installed.

### Installation

1.  Clone the repository to your local machine:
    ```bash
    git clone [https://github.com/PauloBrand7/Calculadora.git](https://github.com/PauloBrand7/Calculadora.git) 
    ```
    *(Nota: Por favor, confirme se a URL do repositório está correta.)*

2.  Open the project in Android Studio.
3.  Wait for Gradle to sync and build the project.
4.  Run the application on an Android emulator or a physical device.
