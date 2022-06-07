package com.commands;

import com.OutputPrinter;
import com.model.Car;
import com.model.Command;
import com.exception.NoFreeSlotAvailableException;
import com.service.ParkingLotService;

/**
 * Executor to handle command of parking a car into the parking lot. This outputs the alloted slot
 * in which the car is parked.
 */
public class ParkCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "park";

  public ParkCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 2;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final Command command) {
    final Car car = new Car(command.getParams().get(0), command.getParams().get(1));

    try {
      final Integer slot = parkingLotService.park(car);

      outputPrinter.printWithNewLine("Car with vehicle registration number "+ car.getRegistrationNumber()+" has been parked at slot number "+ slot);
    } catch (NoFreeSlotAvailableException exception) {
      outputPrinter.parkingLotFull();
    }
  }
}
