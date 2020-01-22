package hotel.main;

import java.util.Locale;

import hotel.dao.HotelDao;
import hotel.dao.HotelDaoImpl;
import hotel.gui.LoginFrame;

import hotel.service.HotelService;
import hotel.service.HotelServiceImpl;

public class MainClass {

    public static void main(String[] args) {
        try {

            Locale.setDefault(Locale.ENGLISH);
            HotelDao hotelDao = new HotelDaoImpl();
            HotelService hotelService = new HotelServiceImpl(hotelDao);
            System.out.print(hotelService.getWorkerList());
            LoginFrame loginFrame = new LoginFrame(hotelService);
            loginFrame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
