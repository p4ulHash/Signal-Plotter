public class SignalPlotter {

    public static final double FIRST_LIMIT = -10.0;
    public static final double SECOND_LIMIT = 10.0;
    public static final int NUMBER_OF_POINTS = 1000;
    public static final int SAMPLING_RATE = 250;

    public static double[] createSamplingPoints(double firstLimit, double secondLimit, int numberOfPoints) {
        // Pruefen, ob Intervallgrenzen gleich sind
        if (firstLimit == secondLimit) {
            numberOfPoints = 1;
        }
        // Array passender Groesse anlegen
        double[] bases = new double[numberOfPoints];
        // Fall 1: Nur ein einziger Stuetzpunkt
        if (numberOfPoints == 1) {
            bases[0] = secondLimit;
            return bases;
        }
        // Fall 2: Mehrere Stuetzpunkte
        double step = (secondLimit - firstLimit) / (double) (numberOfPoints - 1);
        for (int i = 0; i < numberOfPoints; i++) {
            bases[i] = firstLimit + (i * step);
        }
        // Letzten Punkt exakt setzen
        bases[numberOfPoints - 1] = secondLimit;
        return bases;
    }

    public static double sigmoid(double x) {
        // Verwendet Math.exp(-x) fuer e^(-x)
        // Wichtig: Der Nenner (1 + Math.exp(-x)) muss geklammert werden.
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public static double[] applySigmoidToArray(double[] xs) {
        double[] ys = new double[xs.length];
        for (int i = 0; i < xs.length; i++) {
            // Die sigmoid-Methode fuer jeden einzelnen Wert aufrufen
            // und das Ergebnis im neuen Array speichern
            ys[i] = sigmoid(xs[i]);
        }
        return ys;
    }

    public static void plotSigmoid() {
        // Verwendet die oben definierten Klassenkonstanten
        double[] xs = createSamplingPoints(FIRST_LIMIT, SECOND_LIMIT, NUMBER_OF_POINTS);
        // Funktion auf allen Stuetzstellen auswerten
        double[] ys = applySigmoidToArray(xs);
        PlotHelper.plot2D(xs, ys);
    }

    public static void plotEcg() {
        // EKG-Signal einlesen
        double[] ecgSignal = PlotHelper.readEcg("ecg.txt");
        // Anzahl der Stuetzstellen muss Anzahl der EKG-Datenpunkte entsprechen
        int numberOfEcgPoints = ecgSignal.length;
        // (double) verwenden wegen Fliesskomma-Division
        double ecgDuration = (double) numberOfEcgPoints / SAMPLING_RATE;
        // rufen die Methode auf
        double[] ecgTime = createSamplingPoints(0.0, ecgDuration, numberOfEcgPoints);

        int[] idxRPeaks = PlotHelper.readPeaks("rpeaks.txt");//indizien r
        // zacken lesen
        double[] rPeaks = new double [idxRPeaks.length];
        double[] timeRPeaks = new double [idxRPeaks.length];
        //zeitpunkte und zugehoerige datenpunkte ermitteln
        for (int i = 0; i < idxRPeaks.length; i++) {
            int index = idxRPeaks[i];
            //ekg punkt am index von r zacke
            rPeaks[i] = ecgSignal[index];
            //zeitpunkt am index von r
            // zacke
            timeRPeaks[i] = ecgTime[index];
        }
        //aufruf und ausgabe
        computeHeartRate(timeRPeaks);
        PlotHelper.plotEcg(ecgTime, ecgSignal, timeRPeaks, rPeaks);
    }
    public static void computeHeartRate (double[] timeRPeaks) {
        //ausgabe "heart rate" mit  zeilenumbruch davor
        System.out.println("Heart Rate: ");
        //ueber zeitpunkte der r zacken iterieren ab index stelle 1
        for (int i = 1; i < timeRPeaks.length; i++) {
            //zeitdifferenz zwischen zwei r zacken
            double timeDifferences = timeRPeaks[i] - timeRPeaks[i - 1];
            //darstellung in bpm (beats per minute)
            double heartRate = 60.0 / timeDifferences;
            //ausgabe heartrate
            String formattedHeartrate = String.format("%.2f", heartRate);
            System.out.println(formattedHeartrate + " bpm");//bpm wird
            // nachtraeglich zur heartrate ausgegeben
        }
    }
    public static void main(String[] args) {
        plotSigmoid();
        plotEcg();
    }

}