package com.dealfinder.dealfinderprocessor.utils;

public class CoordinatesUtils {
    private static final double EARTH_RADIUS = 6371000; // Радиус Земли в метрах

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    // Метод для вычисления расстояния между двумя точками по их широте и долготе в метрах
    public static Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        if (lat1 == null ||  lon1 == null ||  lat2 == null ||  lon2 == null){
            return -1.0;
        }
        // Преобразуем широту и долготу в радианы
        double lat1Radians = degreesToRadians(lat1);
        double lon1Radians = degreesToRadians(lon1);
        double lat2Radians = degreesToRadians(lat2);
        double lon2Radians = degreesToRadians(lon2);

        // Разницы между координатами
        double deltaLat = lat2Radians - lat1Radians;
        double deltaLon = lon2Radians - lon1Radians;

        // Вычисляем расстояние с помощью формулы гаверсинусов
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Radians) * Math.cos(lat2Radians) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}
