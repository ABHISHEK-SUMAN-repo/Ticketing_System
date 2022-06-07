package com.commands;

import com.OutputPrinter;
import com.model.Command;
import com.model.Slot;
import com.service.ParkingLotService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Executor to handle command of fetching all registration number of cars of a particular DriverAge.
 */
public class DriverAgeToRegNumberCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "Park";

  public DriverAgeToRegNumberCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final Command command) {
    final List<Slot> slotsForDriverAge = parkingLotService.getSlotsForDriverAge(command.getParams().get(0));
    if (slotsForDriverAge.isEmpty()) {
      outputPrinter.notFound();
    } else {
      final String result =
              slotsForDriverAge.stream()
              .map(slot -> slot.getParkedCar().getRegistrationNumber())
              .collect(Collectors.joining(", "));
      outputPrinter.printWithNewLine(result);
    }
  }
}
