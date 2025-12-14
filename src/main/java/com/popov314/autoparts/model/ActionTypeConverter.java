package com.popov314.autoparts.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ActionTypeConverter implements AttributeConverter<ActionType, String> {

  @Override
  public String convertToDatabaseColumn(ActionType attribute) {
    if (attribute == null) {
      return null;
    }
    // Маппинг: Java -> БД
    return switch (attribute) {
      case hire -> "прием";
      case fire -> "увольнение";
      case transfer -> "перевод";
      default -> throw new IllegalArgumentException("Unknown ActionType: " + attribute);
    };
  }

  @Override
  public ActionType convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    // Маппинг: БД -> Java
    return switch (dbData.toLowerCase()) {
      case "прием" -> ActionType.hire;
      case "увольнение" -> ActionType.fire;
      case "перевод" -> ActionType.transfer;
      default -> throw new IllegalArgumentException("Unknown value in DB: " + dbData);
    };
  }
}