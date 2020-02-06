class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    String out = String.format("%02d:%02d:%02d", hours, minutes, seconds);

    @Override
    public String toString() {
        return "\""+out+"\"";
    }
}