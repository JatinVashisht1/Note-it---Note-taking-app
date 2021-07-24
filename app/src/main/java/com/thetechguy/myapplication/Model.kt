package com.thetechguy.myapplication

public class Model {

  public   var isSelected : Boolean = false

   public fun setCardSelected(selected: Boolean) {
        isSelected = selected
    }


   public fun isCardSelected(): Boolean {
        return isSelected
    }


}