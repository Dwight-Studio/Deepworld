package fr.dwightstudio.deepworld.common.machine.wooden_gear_shaper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;

public class WoodenGearShaperStateData implements IIntArray {

    // The number of ticks that the current item has been processing
    public int processTimeElapsed;

    // The number of ticks required to process the current item
    public int processTimeForCompletion;

    // The initial inertia value (in ticks of inertia duration)
    public int inertiaTimeInitialValue;

    // The number of inertia ticks remaining
    public int inertiaTimeRemaining;

    // Read/write to NBT for permanent storage (on disk, or packet transmission) - used by the TileEntity only

    public void putIntoNBT(CompoundNBT nbtTagCompound) {
        nbtTagCompound.putInt("ProcessTimeElapsed", processTimeElapsed);
        nbtTagCompound.putInt("ProcessTimeForCompletion", processTimeElapsed);
        nbtTagCompound.putInt("inertiaTimeRemaining", inertiaTimeRemaining);
        nbtTagCompound.putInt("inertiaTimeInitialValue", inertiaTimeInitialValue);
    }

    public void readFromNBT(CompoundNBT nbtTagCompound) {
        // Trim the arrays (or pad with 0) to make sure they have the correct number of elements
        processTimeElapsed = nbtTagCompound.getInt("ProcessTimeElapsed");
        processTimeForCompletion = nbtTagCompound.getInt("ProcessTimeForCompletion");
        inertiaTimeRemaining = nbtTagCompound.getInt("inertiaTimeRemaining");
        inertiaTimeInitialValue = nbtTagCompound.getInt("inertiaTimeInitialValue");
    }

    private final int PROCESS_TIME_INDEX = 0;
    private final int PROCESS_TIME_FOR_COMPLETION_INDEX = 1;
    private final int INERTIA_TIME_INITIAL_VALUE_INDEX = 2;
    private final int INERTIA_TIME_REMAINING_INDEX = 3;
    private final int END_OF_DATA_INDEX_PLUS_ONE = 4;

    // Getting
    @Override
    public int get(int index) {
        switch (index) {
            case PROCESS_TIME_INDEX:
                return processTimeElapsed;
            case PROCESS_TIME_FOR_COMPLETION_INDEX:
                return processTimeForCompletion;
            case INERTIA_TIME_INITIAL_VALUE_INDEX:
                return inertiaTimeInitialValue;
            case INERTIA_TIME_REMAINING_INDEX:
                return inertiaTimeRemaining;
        }
        throw new IndexOutOfBoundsException("The index must be between " + PROCESS_TIME_INDEX + " and " + INERTIA_TIME_REMAINING_INDEX + " included");
    }

    // Setting
    @Override
    public void set(int index, int value) {
        switch (index) {
            case PROCESS_TIME_INDEX:
                processTimeElapsed = value;
                return;
            case PROCESS_TIME_FOR_COMPLETION_INDEX:
                processTimeForCompletion = value;
                return;
            case INERTIA_TIME_INITIAL_VALUE_INDEX:
                inertiaTimeInitialValue = value;
                return;
            case INERTIA_TIME_REMAINING_INDEX:
                inertiaTimeRemaining = value;
                return;
        }
        throw new IndexOutOfBoundsException("The index must be between " + PROCESS_TIME_INDEX + " and " + INERTIA_TIME_REMAINING_INDEX + " included, not " + index);
    }

    @Override
    public int size() {
        return END_OF_DATA_INDEX_PLUS_ONE;
    }

}
