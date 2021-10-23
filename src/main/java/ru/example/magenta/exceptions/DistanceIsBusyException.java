package ru.example.magenta.exceptions;

import ru.example.magenta.dto.CityDto;
import ru.example.magenta.dto.DistanceDto;

public class DistanceIsBusyException extends RuntimeException {
    public DistanceIsBusyException(DistanceDto distanceDTO) {
        super("данное направление занято : " + distanceDTO.getFromCity() + ":" +
                distanceDTO.getToCity());
    }

    public DistanceIsBusyException(CityDto fromCity, CityDto toCity) {
        super("данное направление занято : " + fromCity.getName() + ":" +
                toCity.getName());
    }
}
