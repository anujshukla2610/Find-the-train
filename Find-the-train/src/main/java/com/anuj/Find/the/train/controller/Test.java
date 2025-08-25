package com.anuj.Find.the.train.controller;

import com.anuj.Find.the.train.enitity.Station;
import com.anuj.Find.the.train.enitity.Train;
import com.anuj.Find.the.train.enitity.TrainSchedule;
import com.anuj.Find.the.train.repo.StationRepository;
import com.anuj.Find.the.train.repo.TrainRepository;
import com.anuj.Find.the.train.repo.TrainScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*") // Allow frontend to connect
public class Test {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainScheduleRepository trainScheduleRepository;

    @GetMapping
    public String test() {
        try {
            // Clear existing data first (recommended for fresh data)
            trainScheduleRepository.deleteAll();
            trainRepository.deleteAll();
            stationRepository.deleteAll();

            // Create Stations
            Station delhi = new Station(null, "New Delhi", "NDLS");
            Station mumbai = new Station(null, "Mumbai Central", "BCT");
            Station chennai = new Station(null, "Chennai Central", "MAS");
            Station kolkata = new Station(null, "Howrah Junction", "HWH");
            Station bangalore = new Station(null, "Bengaluru City Junction", "SBC");
            Station hyderabad = new Station(null, "Hyderabad Deccan", "HYB");
            Station pune = new Station(null, "Pune Junction", "PUNE");
            Station jaipur = new Station(null, "Jaipur Junction", "JP");
            Station lucknow = new Station(null, "Lucknow NR", "LKO");
            Station patna = new Station(null, "Patna Junction", "PNBE");
            Station ahmedabad = new Station(null, "Ahmedabad Junction", "ADI");
            Station bhopal = new Station(null, "Bhopal Junction", "BPL");

            List<Station> savedStations = stationRepository.saveAll(List.of(delhi, mumbai, kolkata, chennai, bangalore,
                    hyderabad, pune, jaipur, lucknow, patna, ahmedabad, bhopal));

            // Create Trains with unique numbers
            Train rajdhani1 = new Train(null, "Rajdhani Express", "12306", null);
            Train rajdhani2 = new Train(null, "Mumbai Rajdhani", "12952", null);
            Train rajdhani3 = new Train(null, "Chennai Rajdhani", "12434", null);
            Train shatabdi1 = new Train(null, "Shatabdi Express", "12001", null);
            Train shatabdi2 = new Train(null, "Jan Shatabdi", "12063", null);
            Train duronto1 = new Train(null, "Duronto Express", "12273", null);
            Train duronto2 = new Train(null, "Mumbai Duronto", "12274", null);
            Train garibRath = new Train(null, "Garib Rath Express", "12957", null);
            Train mail = new Train(null, "Howrah Mail", "12321", null);
            Train superfast1 = new Train(null, "Superfast Express", "12951", null);
            Train superfast2 = new Train(null, "Double Decker", "12028", null);
            Train samparkKranti = new Train(null, "Sampark Kranti Express", "12907", null);
            Train tejas = new Train(null, "Tejas Express", "22119", null);
            Train acExpress = new Train(null, "AC Express", "22455", null);
            Train chennaiExpress = new Train(null, "Chennai Express", "12615", null);
            Train grandTrunk = new Train(null, "Grand Trunk Express", "12617", null);
            Train bangaloreExpress = new Train(null, "Bangalore Express", "12639", null);
            Train hyderabadExpress = new Train(null, "Hyderabad Express", "12723", null);
            Train intercity = new Train(null, "Intercity Express", "12345", null);
            Train passenger = new Train(null, "Passenger Train", "56789", null);

            List<Train> savedTrains = trainRepository.saveAll(List.of(rajdhani1, rajdhani2, rajdhani3, shatabdi1, shatabdi2,
                    duronto1, duronto2, garibRath, mail, superfast1, superfast2,
                    samparkKranti, tejas, acExpress, chennaiExpress, grandTrunk,
                    bangaloreExpress, hyderabadExpress, intercity, passenger));

            // Get saved entities to ensure proper IDs
            delhi = savedStations.stream().filter(s -> "NDLS".equals(s.getStationCode())).findFirst().orElse(null);
            mumbai = savedStations.stream().filter(s -> "BCT".equals(s.getStationCode())).findFirst().orElse(null);
            chennai = savedStations.stream().filter(s -> "MAS".equals(s.getStationCode())).findFirst().orElse(null);
            kolkata = savedStations.stream().filter(s -> "HWH".equals(s.getStationCode())).findFirst().orElse(null);
            bangalore = savedStations.stream().filter(s -> "SBC".equals(s.getStationCode())).findFirst().orElse(null);
            hyderabad = savedStations.stream().filter(s -> "HYB".equals(s.getStationCode())).findFirst().orElse(null);
            pune = savedStations.stream().filter(s -> "PUNE".equals(s.getStationCode())).findFirst().orElse(null);
            jaipur = savedStations.stream().filter(s -> "JP".equals(s.getStationCode())).findFirst().orElse(null);
            patna = savedStations.stream().filter(s -> "PNBE".equals(s.getStationCode())).findFirst().orElse(null);
            ahmedabad = savedStations.stream().filter(s -> "ADI".equals(s.getStationCode())).findFirst().orElse(null);
            bhopal = savedStations.stream().filter(s -> "BPL".equals(s.getStationCode())).findFirst().orElse(null);

            // Get saved trains
            rajdhani1 = savedTrains.stream().filter(t -> "12306".equals(t.getTrainNumber())).findFirst().orElse(null);
            rajdhani2 = savedTrains.stream().filter(t -> "12952".equals(t.getTrainNumber())).findFirst().orElse(null);
            rajdhani3 = savedTrains.stream().filter(t -> "12434".equals(t.getTrainNumber())).findFirst().orElse(null);
            shatabdi1 = savedTrains.stream().filter(t -> "12001".equals(t.getTrainNumber())).findFirst().orElse(null);
            shatabdi2 = savedTrains.stream().filter(t -> "12063".equals(t.getTrainNumber())).findFirst().orElse(null);
            duronto1 = savedTrains.stream().filter(t -> "12273".equals(t.getTrainNumber())).findFirst().orElse(null);
            duronto2 = savedTrains.stream().filter(t -> "12274".equals(t.getTrainNumber())).findFirst().orElse(null);
            garibRath = savedTrains.stream().filter(t -> "12957".equals(t.getTrainNumber())).findFirst().orElse(null);
            mail = savedTrains.stream().filter(t -> "12321".equals(t.getTrainNumber())).findFirst().orElse(null);
            superfast1 = savedTrains.stream().filter(t -> "12951".equals(t.getTrainNumber())).findFirst().orElse(null);
            superfast2 = savedTrains.stream().filter(t -> "12028".equals(t.getTrainNumber())).findFirst().orElse(null);
            samparkKranti = savedTrains.stream().filter(t -> "12907".equals(t.getTrainNumber())).findFirst().orElse(null);
            tejas = savedTrains.stream().filter(t -> "22119".equals(t.getTrainNumber())).findFirst().orElse(null);
            acExpress = savedTrains.stream().filter(t -> "22455".equals(t.getTrainNumber())).findFirst().orElse(null);
            chennaiExpress = savedTrains.stream().filter(t -> "12615".equals(t.getTrainNumber())).findFirst().orElse(null);
            grandTrunk = savedTrains.stream().filter(t -> "12617".equals(t.getTrainNumber())).findFirst().orElse(null);
            bangaloreExpress = savedTrains.stream().filter(t -> "12639".equals(t.getTrainNumber())).findFirst().orElse(null);
            hyderabadExpress = savedTrains.stream().filter(t -> "12723".equals(t.getTrainNumber())).findFirst().orElse(null);
            intercity = savedTrains.stream().filter(t -> "12345".equals(t.getTrainNumber())).findFirst().orElse(null);
            passenger = savedTrains.stream().filter(t -> "56789".equals(t.getTrainNumber())).findFirst().orElse(null);

            // Create comprehensive train schedules - EVERY MAJOR ROUTE COVERED

            // NDLS (New Delhi) Routes
            TrainSchedule sc1 = new TrainSchedule(null, rajdhani1, delhi, mumbai, "16:55", "08:35+1");
            TrainSchedule sc2 = new TrainSchedule(null, rajdhani2, delhi, mumbai, "20:30", "14:15+1");
            TrainSchedule sc3 = new TrainSchedule(null, rajdhani3, delhi, chennai, "15:50", "09:45+1");
            TrainSchedule sc4 = new TrainSchedule(null, chennaiExpress, delhi, chennai, "22:30", "28:15+1");
            TrainSchedule sc5 = new TrainSchedule(null, grandTrunk, delhi, chennai, "19:30", "17:30+1");
            TrainSchedule sc6 = new TrainSchedule(null, bangaloreExpress, delhi, bangalore, "20:15", "04:30+2");
            TrainSchedule sc7 = new TrainSchedule(null, hyderabadExpress, delhi, hyderabad, "17:45", "09:15+1");
            TrainSchedule sc8 = new TrainSchedule(null, shatabdi1, delhi, jaipur, "06:05", "10:30");
            TrainSchedule sc9 = new TrainSchedule(null, mail, delhi, kolkata, "17:05", "10:05+1");
            TrainSchedule sc10 = new TrainSchedule(null, samparkKranti, delhi, pune, "11:50", "05:25+1");

            // BCT (Mumbai Central) Routes
            TrainSchedule sc11 = new TrainSchedule(null, rajdhani1, mumbai, delhi, "16:30", "08:30+1");
            TrainSchedule sc12 = new TrainSchedule(null, rajdhani2, mumbai, delhi, "20:05", "12:25+1");
            TrainSchedule sc13 = new TrainSchedule(null, duronto1, mumbai, chennai, "11:40", "14:20+1");
            TrainSchedule sc14 = new TrainSchedule(null, superfast1, mumbai, bangalore, "22:00", "13:30+1");
            TrainSchedule sc15 = new TrainSchedule(null, tejas, mumbai, pune, "15:25", "18:55");
            TrainSchedule sc16 = new TrainSchedule(null, shatabdi2, mumbai, pune, "07:10", "10:25");
            TrainSchedule sc17 = new TrainSchedule(null, intercity, mumbai, ahmedabad, "06:40", "13:25");

            // MAS (Chennai Central) Routes
            TrainSchedule sc18 = new TrainSchedule(null, rajdhani3, chennai, delhi, "20:55", "16:45+2");
            TrainSchedule sc19 = new TrainSchedule(null, chennaiExpress, chennai, delhi, "14:00", "16:45+1");
            TrainSchedule sc20 = new TrainSchedule(null, grandTrunk, chennai, delhi, "18:40", "17:30+1");
            TrainSchedule sc21 = new TrainSchedule(null, duronto1, chennai, mumbai, "08:30", "07:15+1");
            TrainSchedule sc22 = new TrainSchedule(null, superfast1, chennai, bangalore, "22:50", "06:15+1");
            TrainSchedule sc23 = new TrainSchedule(null, superfast2, chennai, bangalore, "06:00", "12:30");
            TrainSchedule sc24 = new TrainSchedule(null, acExpress, chennai, hyderabad, "17:40", "05:15+1");

            // HWH (Howrah Junction) Routes
            TrainSchedule sc25 = new TrainSchedule(null, mail, kolkata, delhi, "22:50", "17:05+1");
            TrainSchedule sc26 = new TrainSchedule(null, garibRath, kolkata, delhi, "15:50", "10:20+1");
            TrainSchedule sc27 = new TrainSchedule(null, duronto2, kolkata, bangalore, "20:05", "04:25+2");
            TrainSchedule sc28 = new TrainSchedule(null, acExpress, kolkata, chennai, "08:40", "14:40+1");

            // SBC (Bangalore) Routes
            TrainSchedule sc29 = new TrainSchedule(null, bangaloreExpress, bangalore, delhi, "20:00", "05:50+2");
            TrainSchedule sc30 = new TrainSchedule(null, superfast1, bangalore, mumbai, "11:50", "06:30+1");
            TrainSchedule sc31 = new TrainSchedule(null, superfast1, bangalore, chennai, "15:00", "22:15");
            TrainSchedule sc32 = new TrainSchedule(null, superfast2, bangalore, chennai, "07:15", "13:45");
            TrainSchedule sc33 = new TrainSchedule(null, duronto2, bangalore, kolkata, "17:15", "09:25+2");

            // HYB (Hyderabad) Routes
            TrainSchedule sc34 = new TrainSchedule(null, hyderabadExpress, hyderabad, delhi, "21:50", "19:15+1");
            TrainSchedule sc35 = new TrainSchedule(null, acExpress, hyderabad, chennai, "05:40", "17:40");
            TrainSchedule sc36 = new TrainSchedule(null, intercity, hyderabad, bangalore, "06:00", "13:30");

            // Additional popular cross-connections
            TrainSchedule sc37 = new TrainSchedule(null, shatabdi1, jaipur, delhi, "17:50", "22:15");
            TrainSchedule sc38 = new TrainSchedule(null, shatabdi2, pune, mumbai, "18:10", "21:25");
            TrainSchedule sc39 = new TrainSchedule(null, samparkKranti, pune, delhi, "22:10", "14:50+1");
            TrainSchedule sc40 = new TrainSchedule(null, passenger, patna, delhi, "07:30", "02:15+1");
            TrainSchedule sc41 = new TrainSchedule(null, intercity, ahmedabad, mumbai, "16:45", "23:30");
            TrainSchedule sc42 = new TrainSchedule(null, garibRath, bhopal, delhi, "02:15", "10:50");

            trainScheduleRepository.saveAll(List.of(
                    sc1, sc2, sc3, sc4, sc5, sc6, sc7, sc8, sc9, sc10,
                    sc11, sc12, sc13, sc14, sc15, sc16, sc17, sc18, sc19, sc20,
                    sc21, sc22, sc23, sc24, sc25, sc26, sc27, sc28, sc29, sc30,
                    sc31, sc32, sc33, sc34, sc35, sc36, sc37, sc38, sc39, sc40,
                    sc41, sc42
            ));

            System.out.println("✅ Comprehensive train data inserted in database!");
            System.out.println("📊 Database Summary:");
            System.out.println("   • Stations: 12");
            System.out.println("   • Trains: 20");
            System.out.println("   • Schedules: 42");

            return "✅ Database initialized successfully! Check console for details.";

        } catch (Exception e) {
            System.err.println("❌ Error initializing database: " + e.getMessage());
            e.printStackTrace();
            return "❌ Error initializing database: " + e.getMessage();
        }
    }

    // Additional endpoint to check data
    @GetMapping("/status")
    public String getStatus() {
        long stationCount = stationRepository.count();
        long trainCount = trainRepository.count();
        long scheduleCount = trainScheduleRepository.count();

        return String.format("Database Status:\n• Stations: %d\n• Trains: %d\n• Schedules: %d",
                stationCount, trainCount, scheduleCount);
    }
}