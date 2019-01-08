package com.vinaysshenoy.gildedrosekata

import java.util.Locale

fun main(args: Array<String>) {
  val items = listOf(
      Item(name = "foo 1", sellIn = 30, quality = 50),
      Item(name = "foo 2", sellIn = 10, quality = 5)
  )

  val application = Application(itemsInStock = items, runForDays = 20)
  application.run()
}

class Application(itemsInStock: List<Item>, val runForDays: Int) {

  private val headLine = String.format(Locale.ENGLISH, "%1\$-50s %2\$7s %3\$8s", "Name", "Sell In", "Quality")
  private val itemFormatPattern = "%1\$-50s %2\$7d %3\$8d"
  private val gildedRose = GildedRose(items = itemsInStock.toTypedArray())

  fun run() {
    (0 until runForDays).forEach { dayIndex ->
      println("---- Day $dayIndex ----")
      println(headLine)
      gildedRose.items.forEach { item ->
        println(String.format(Locale.ENGLISH, itemFormatPattern, item.name, item.sellIn, item.quality))
      }
      println()
      gildedRose.updateQuality()
    }
  }
}
