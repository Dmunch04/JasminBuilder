package me.munchii.Jasmin.type;

import java.util.Objects;

public interface IDataType {
    String getRepresentation();

    default String getIdentifier() {
        return getRepresentation();
    }

    default boolean isPrimitive() {
        return false;
    }
    default boolean isArray() {
        return false;
    }
    default boolean isReference() {
        return false;
    }
    default boolean isVoid() {
        return false;
    }

    default boolean compare(IDataType other) {
        return Objects.equals(getRepresentation(), other.getRepresentation());
    }

    default boolean compareSuper(IDataType other) {
        if (isVoid() && other.isVoid()) {
            return true;
        }

        if (other instanceof ValueType) {
            return compare(other);
        }

        if (this.isArray()) {
            return other instanceof ArrayType;
        } else if (this.isReference()) {
            return other instanceof ReferenceType;
        }

        return false;
    }
}
