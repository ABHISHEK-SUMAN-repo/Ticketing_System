package com.model;

/**
 * Model object to represent a car.
 */
public class Car {
  private String registrationNumber;
  private String driverAge;

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public String getDriverAge() {
    return driverAge;
  }

  public Car(final String registrationNumber, final String driverAge) {
    this.registrationNumber = registrationNumber;
    this.driverAge = driverAge;
  }
}
