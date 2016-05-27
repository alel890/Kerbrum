package ar.com.kerbrum.kerbrum;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;

public class Medicamento implements Parcelable{
	String nombre,inicio,unidad;
	String hora;
	ArrayList <String> diaArray;
	int horaDev;
	int minutoDev;
	int forma;
	int color;
	float dosis;
	int stock;
	int turno;
	int diaSize;
    int duracion;
    //Medicamento (id autoincremental, nombre, color, forma, unidad, duracion, inicio, turno, stock)


	public Medicamento (){



	}
    public Medicamento(String nombre, ArrayList <String> d, int forma, int color, float dosis, int hora, int minuto, String unidad, int duracion, String inicio, int turno, int stock){
		this.nombre=nombre;
		diaArray=d;
		this.hora=Integer.toString(hora) +":"+ Integer.toString(minuto);
		this.horaDev=hora;
        this.minutoDev=minuto;
		this.forma=forma;
		this.color=color;
		this.dosis=dosis;
		this.turno=turno;
		this.stock=stock;
        this.duracion=duracion;
		this.inicio=inicio;
		this.unidad=unidad;

	}




	public Medicamento(Parcel in) {
//        creates a country object from the contents of the parcel
		nombre=in.readString();
		diaArray= in.readArrayList(Medicamento.class.getClassLoader());
		hora=in.readString();
		horaDev=in.readInt();
		minutoDev=in.readInt();
		forma=in.readInt();
		color=in.readInt();
		dosis=in.readFloat();
		turno=in.readInt();
		stock=in.readInt();
		duracion=in.readInt();
		inicio=in.readString();
		unidad=in.readString();
	}



//NOMBRE

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

//DIA

    public void setDiaArray (ArrayList<String> arraydia){this.diaArray=arraydia; }
    public ArrayList<String> getDiaArray(){return diaArray;	}
	public int getDiaSize(){
		return this.diaSize;
	}

//HORA

	public int getMinuto(){	return minutoDev;}
	public int getHoraInt(){return horaDev;	}
	public String getHora() {return hora;}
	public void setHora(int hora, int minuto) {
		this.minutoDev=minuto;
		this.horaDev=hora;
		this.hora = Integer.toString(hora) +":"+ Integer.toString(minuto);
	}

//FORMA

	public int getForma() {
		return forma;
	}
	public void setForma(int forma) {
		this.forma = forma;
	}

//COLOR

	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

//DOSIS

	public float getDosis() {
		return dosis;
	}
	public void setDosis(float dosis) {
		this.dosis = dosis;
	}

//TURNO

	public int getTurno(){return turno;}
	public void setTurno(int turno){this.turno=turno;}

//STOCK

	public float getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
//DURACION
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
//UNIDAD
    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
//INICIO
    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(nombre);
			dest.writeString(inicio);
			dest.writeString(unidad);
			dest.writeString(hora);
			dest.writeList(diaArray);
			dest.writeInt(horaDev);
			dest.writeInt(minutoDev);
			dest.writeInt(forma);
			dest.writeFloat(dosis);
			dest.writeInt(turno);
			dest.writeInt(duracion);
			dest.writeInt(stock);
			dest.writeInt(color);



	}
	public static final Parcelable.Creator<Medicamento> CREATOR =
			new Parcelable.Creator<Medicamento>() {
				@Override
				public Medicamento createFromParcel(Parcel source) {
					return new Medicamento(source);
				}
				@Override
				public Medicamento [] newArray(int size) {
					return new Medicamento[size];
				}
			};
}
