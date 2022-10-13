package model;

import java.util.concurrent.Future;

public class Element {

  private Future<Integer> value;

  public Future<Integer> getValue() {
    return value;
  }

  public void setValue(Future<Integer> value) {
    this.value = value;
  }

}
