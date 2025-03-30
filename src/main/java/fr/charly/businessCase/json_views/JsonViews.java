package fr.charly.businessCase.json_views;

public class JsonViews {

    public interface UserMinimalView {}
    public interface UserShowView extends
            UserMinimalView {}

    public interface ChargingStationMinimalView {}
    public interface ChargingStationListView extends ChargingStationMinimalView {}
    public interface ChargingStationShowView extends ChargingStationListView {}


    public interface BookingMinimalView {};
    public  interface UserReviewMinimalView {}
    public  interface ReviewMinimalView {}
    public  interface HourlyRateMinimalView {}
    public interface PowerMinimalView{}

}
