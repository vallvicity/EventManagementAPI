package com.example.demo.dto;

import com.example.demo.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.example.demo.enums.Status.PENDING;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    @NotNull
    private Long attendeeId;
    @NotNull
    private Long eventId;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(Long attendeeId) {
        this.attendeeId = attendeeId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
