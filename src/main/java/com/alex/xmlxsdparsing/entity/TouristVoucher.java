package com.alex.xmlxsdparsing.entity;

import com.alex.xmlxsdparsing.entity.enumerationvalue.VoucherType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TouristVoucher extends CustomVoucher {

    private VoucherType voucherType;
    private List<String> country;
    private int numberDays;
    private int numberNights;
    private LocalDateTime dateTime;
    private List<String> transport;
    private List<Hotel> hotel;
    private Cost cost;

    public TouristVoucher(){
        this.country = new ArrayList<>();
        this.transport= new ArrayList<>();
        this.hotel= new ArrayList<>();
    }

    public TouristVoucher(VoucherType voucherType, List<String> country, int numberDays, int numberNights, LocalDateTime dateTime, List<String> transport, List<Hotel> hotel, Cost cost){
        this.country = new ArrayList<>();
        this.transport= new ArrayList<>();
        this.hotel= new ArrayList<>();
        this.voucherType = voucherType;
        this.country = country;
        this.numberDays = numberDays;
        this.numberNights = numberNights;
        this.dateTime = dateTime;
        this.transport = transport;
        this.hotel = hotel;
        this.cost = cost;
    }

    public VoucherType getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(VoucherType voucherType) {
        this.voucherType = voucherType;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public void addCountry(String item){
        this.country.add(item);
    }

    public void removeCountry(String item){
        this.country.remove(item);
    }

    public void clearCountry(){
        this.country.clear();
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public int getNumberNights() {
        return numberNights;
    }

    public void setNumberNights(int numberNights) {
        this.numberNights = numberNights;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getTransport() {
        return transport;
    }

    public void setTransport(List<String> transport) {
        this.transport = transport;
    }

    public void addTransport(String item){
        this.transport.add(item);
    }

    public void removeTransport(String item){
        this.transport.remove(item);
    }

    public void clearTransport(){
        this.transport.clear();
    }

    public List<Hotel> getHotel() {
        return hotel;
    }

    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
    }

    public void addHotel(Hotel item){
        this.hotel.add(item);
    }

    public void removeHotel(Hotel item){
        this.hotel.remove(item);
    }

    public void clearHotel(){
        this.hotel.clear();
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TouristVoucher{");
        sb.append("voucherType=").append(voucherType);
        sb.append(", country=").append(country);
        sb.append(", numberDays=").append(numberDays);
        sb.append(", numberNights=").append(numberNights);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", transport=").append(transport);
        sb.append(", hotel=").append(hotel);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TouristVoucher that = (TouristVoucher) o;

        if (numberDays != that.numberDays) return false;
        if (numberNights != that.numberNights) return false;
        if (voucherType != that.voucherType) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (transport != null ? !transport.equals(that.transport) : that.transport != null) return false;
        if (hotel != null ? !hotel.equals(that.hotel) : that.hotel != null) return false;
        return cost != null ? cost.equals(that.cost) : that.cost == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (voucherType != null ? voucherType.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + numberDays;
        result = 31 * result + numberNights;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
