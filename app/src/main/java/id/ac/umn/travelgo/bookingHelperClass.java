package id.ac.umn.travelgo;

public class bookingHelperClass {

    String data1, data2, data3, data4;

    public bookingHelperClass(String user, String id, String harga, String namahotel){
        this.data1 = user;
        this.data2 = id;
        this.data3 = harga;
        this.data4 = namahotel;
    }

    public String getUsername() {
        return data1;
    }

    public void setUsername(String username) {
        this.data1 = username;
    }

    public String getId() {
        return data2;
    }

    public void setId(String id) {
        this.data2 = id;
    }

    public String getHarga() {
        return data3;
    }

    public void setHarga(String harga) {
        this.data3 = harga;
    }

    public String getHotel() {
        return data4;
    }

    public void setHotel(String hotel) {
        this.data4 = hotel;
    }



}
// Script By Christian Liyanto - 00000033739