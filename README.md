# ? Java Signal Plotter & ECG Analysis

> A graphical user interface application for generating mathematical sampling points, plotting sigmoid functions, and analyzing bio-signals in Java.

## ? About The Project
This project was developed as part of an Algorithms and Data Structures university course. It demonstrates how to integrate external charting libraries (`jmathplot`), parse text data files, and visualize numerical datasets and biological signals.

### ? The Assignment
The objective was to build a signal plotting tool capable of rendering discrete 2D functions and real-world medical data in a graphical user interface.

**Core Features:**
* **Sampling Point Generation:** Implements `createSamplingPoints` to calculate equidistant values across custom intervals with strict boundary checking.
* **Function Plotting:** Evaluates and plots the mathematical sigmoid function ($sig(x) = \frac{1}{1+e^{-x}}$) over a specified interval using 1000 sampling points.
* **ECG Data Processing:** Reads electrocardiogram (ECG) signal data from a text file (`ecg.txt`) sampled at a rate of 250 Hz and plots the temporal waves.
* **R-Peak Detection & Heart Rate Calculation:** Parses R-peak indices from a secondary file (`rpeaks.txt`), highlights these peaks on the graphical plot, and calculates the resulting heart rate in beats per minute (bpm), rounded to two decimal places.

## ?? Built With
* **Language:** Java
* **Libraries:** `jmathplot` (2D plotting library), custom `PlotHelper` framework
* **IDE:** IntelliJ IDEA
* **Core Concepts:** Data Parsing, Numerical Sampling, Graphical Plotting, Bio-signal Analysis

## ? Getting Started
1. Clone the repository: `git clone https://github.com/p4ulHash/Signal-Plotter.git`
2. Open the project in IntelliJ IDEA.
3. Ensure the `jmathplot.jar` library is added to your project dependencies.
4. Place the required data files (`ecg.txt` and `rpeaks.txt`) in the root directory of the project.
5. Run the application via the `SignalPlotter` main class.

## ? Author
**Paul Lang**
* GitHub: [@p4ulHash](https://github.com/p4ulHash)