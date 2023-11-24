package me.munchii.Jasmin.type;

public class VoidType implements IDataType {
    @Override
    public String getRepresentation() {
        return "V";
    }

    @Override
    public boolean isVoid() {
        return true;
    }
}
