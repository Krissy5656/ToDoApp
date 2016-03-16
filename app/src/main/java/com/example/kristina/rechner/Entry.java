package com.example.kristina.rechner;

/**
 * Created by Kristina on 14.01.2016.
 */
public class Entry {
    private long id;
    private int zahl1;
    private String operator;
    private int zahl2;
    private int ergebnis;

    public Entry() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getZahl1() {
        return this.zahl1;
    }

    public void setZahl1 (int zahl1) {
        this.zahl1 = zahl1;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getZahl2() {
        return this.zahl2;
    }

    public void setZahl2(int zahl2) {
        this.zahl2 = zahl2;
    }

    public String toString() {
        if(this.operator.equals("+")) {
            this.ergebnis = this.zahl1 + this.zahl2;
        } else if(this.operator.equals("-")) {
            this.ergebnis = this.zahl1 - this.zahl2;
        } else if(this.operator.equals("*")) {
            this.ergebnis = this.zahl1 * this.zahl2;
        } else if(this.operator.equals("/")) {
            this.ergebnis = this.zahl1 / this.zahl2;
        }
        //Formartiert damit es nur noch ein großer string ist      //%D steht für eine zahl  %s steht für den string also die lücke
        return String.format("Rechnung: %d %s %d = %d", new Object[]{Integer.valueOf(this.zahl1), this.operator, Integer.valueOf(this.zahl2), Integer.valueOf(this.ergebnis)});
    }
}