package me.munchii.Jasmin.type;

public class VoidType implements ReturnableType {
    @Override
    public String getRepresentation() {
        return "V";
    }

    @Override
    public boolean isVoid() {
        return true;
    }
}
