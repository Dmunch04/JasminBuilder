package me.munchii.Jasmin.method;

import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.type.*;
import me.munchii.Jasmin.value.JasminValue;

import java.util.ArrayList;
import java.util.List;

public class VariableReference extends LocalVariable {
    // TODO: allow to reassign this somehow (maybe this already works with super.storeNewValue(value);
    public final JasminType variableType;

    public VariableReference(String name, int id, JasminType variableType) {
        super(name, id, new JasminValue(null, variableType));

        this.variableType = variableType;
    }

    @Override
    public List<IJasminInstruction> declare() {
        return super.declare();
    }

    @Override
    public List<IJasminInstruction> load() {
        String index = String.valueOf(id);
        List<IJasminInstruction> instructions = new ArrayList<>();
        if (variableType instanceof ValueType valueType) {
            switch (valueType) {
                case BOOLEAN, SHORT, INTEGER, BYTE, CHAR -> instructions.add(new Instruction(JasminInstructions.LOAD_INTEGER, index));
                case DOUBLE -> instructions.add(new Instruction(JasminInstructions.LOAD_DOUBLE, index));
                case FLOAT -> instructions.add(new Instruction(JasminInstructions.LOAD_FLOAT, index));
                case LONG -> instructions.add(new Instruction(JasminInstructions.LOAD_LONG, index));
            };
        } else if (variableType instanceof VoidType) {
            instructions.add(new Instruction(JasminInstructions.NOP, " ; dont do this"));
        } else if (variableType instanceof ReferenceType referenceType) {
            instructions.add(new Instruction(JasminInstructions.LOAD_REFERENCE, index));
        } else if (variableType instanceof ClassType classType) {
            instructions.add(new Instruction(JasminInstructions.LOAD_REFERENCE, index));
        } else if (variableType instanceof ArrayType arrayType) {
            instructions.add(new Instruction(JasminInstructions.LOAD_REFERENCE, index));
        } else {
            instructions.add(new Instruction(JasminInstructions.NOP, " ; idk?"));
        }

        return instructions;
    }

    @Override
    public List<IJasminInstruction> store() {
        String index = String.valueOf(id);
        List<IJasminInstruction> instructions = new ArrayList<>();
        if (variableType instanceof ValueType valueType) {
            switch (valueType) {
                case BOOLEAN, BYTE, CHAR, INTEGER, SHORT -> instructions.add(new Instruction(JasminInstructions.STORE_INTEGER, index));
                case DOUBLE -> instructions.add(new Instruction(JasminInstructions.STORE_DOUBLE, index));
                case FLOAT -> instructions.add(new Instruction(JasminInstructions.STORE_FLOAT, index));
                case LONG -> instructions.add(new Instruction(JasminInstructions.STORE_LONG, index));
            };
        } else if (variableType instanceof VoidType) {
            instructions.add(new Instruction(JasminInstructions.NOP, " ; dont do this"));
        } else if (variableType instanceof ReferenceType referenceType) {
            instructions.add(new Instruction(JasminInstructions.STORE_REFERENCE, index));
        } else if (variableType instanceof ClassType classType) {
            instructions.add(new Instruction(JasminInstructions.STORE_REFERENCE, index));
        } else if (variableType instanceof StringType stringType) {
            instructions.add(new Instruction(JasminInstructions.STORE_REFERENCE, index));
        } else if (variableType instanceof ArrayType arrayType) {
            // TODO ?
            instructions.add(new Instruction(JasminInstructions.STORE_REFERENCE, index));
        } else {
            instructions.add(new Instruction(JasminInstructions.NOP, " ; idk?"));
        }

        return instructions;
    }
}
