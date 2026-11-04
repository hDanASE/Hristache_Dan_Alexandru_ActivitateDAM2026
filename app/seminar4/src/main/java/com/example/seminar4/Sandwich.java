package com.example.seminar4;

import android.os.Parcel;
import android.os.Parcelable;

public class Sandwich implements Parcelable {

    private String nume;
    private double pret;
    private boolean vegetarian;
    private String marime;
    private String tipPaine;
    private float rating;
    private boolean cuSos;

    public Sandwich(String nume, double pret, boolean vegetarian, String marime,
                    String tipPaine, float rating, boolean cuSos) {
        this.nume = nume;
        this.pret = pret;
        this.vegetarian = vegetarian;
        this.marime = marime;
        this.tipPaine = tipPaine;
        this.rating = rating;
        this.cuSos = cuSos;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getMarime() {
        return marime;
    }

    public void setMarime(String marime) {
        this.marime = marime;
    }

    public String getTipPaine() {
        return tipPaine;
    }

    public void setTipPaine(String tipPaine) {
        this.tipPaine = tipPaine;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isCuSos() {
        return cuSos;
    }

    public void setCuSos(boolean cuSos) {
        this.cuSos = cuSos;
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                ", vegetarian=" + vegetarian +
                ", marime='" + marime + '\'' +
                ", tipPaine='" + tipPaine + '\'' +
                ", rating=" + rating +
                ", cuSos=" + cuSos +
                '}';
    }

    // Parcelable

    protected Sandwich(Parcel in) {
        nume = in.readString();
        pret = in.readDouble();
        vegetarian = in.readByte() != 0;
        marime = in.readString();
        tipPaine = in.readString();
        rating = in.readFloat();
        cuSos = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeDouble(pret);
        dest.writeByte((byte) (vegetarian ? 1 : 0));
        dest.writeString(marime);
        dest.writeString(tipPaine);
        dest.writeFloat(rating);
        dest.writeByte((byte) (cuSos ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sandwich> CREATOR = new Creator<Sandwich>() {
        @Override
        public Sandwich createFromParcel(Parcel in) {
            return new Sandwich(in);
        }

        @Override
        public Sandwich[] newArray(int size) {
            return new Sandwich[size];
        }
    };
}
