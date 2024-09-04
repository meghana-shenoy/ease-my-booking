package com.example.easemybooking.service;

import com.example.easemybooking.model.Cancellation;

public interface CancellationService {
    Cancellation addCancellation(Cancellation cancellation);
    Cancellation findCancellation(int id);
}
