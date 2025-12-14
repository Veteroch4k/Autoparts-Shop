package com.popov314.autoparts.model;

import lombok.Getter;

@Getter
public enum ActionType {
  hire("прием"),
  fire("увольнение"),
  transfer("перевод");

  private final String title;

  ActionType(String title) {
    this.title = title;
  }

}