package ma.youcode.gathergrid.mapper;

import jakarta.enterprise.inject.Model;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.dto.EventDto;

@Model
public class EventDtoMapper {
    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setLocation(event.getLocation());
        eventDto.setDate(event.getDateTime());
        return eventDto;
    }
}
