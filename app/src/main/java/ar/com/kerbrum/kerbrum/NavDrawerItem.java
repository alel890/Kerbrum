package ar.com.kerbrum.kerbrum;

/**
 * Created by iAle7 on 16/08/2015.
 */
public class NavDrawerItem {
    private String titulo;
    private int icon;
    private String count ="0";
    private boolean isCounterVisible = false;
    public NavDrawerItem(){}

    public NavDrawerItem(String titulo, int icon){
        this.titulo= titulo;
        this.icon= icon;
    }
    public NavDrawerItem(String titulo, int icon, boolean isCounterVisible, String count){
        this.titulo= titulo;
        this.icon= icon;
        this.isCounterVisible = isCounterVisible;
        this.count = count;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public int getIcon(){
        return this.icon;
    }
    String getCount(){
        return this.count;
    }
    boolean getCounterVisibility(){
        return this.isCounterVisible;
    }
    public void setTiutlo(String titulo){
        this.titulo= titulo;
    }
    public void setIcon(int icon){
        this.icon = icon;
    }
    public void setCounterVisibility(boolean isCounterVisible){
        this.isCounterVisible = isCounterVisible;
    }
}

