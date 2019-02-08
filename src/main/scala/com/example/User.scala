package com.example

class User(first: String, last: String, born: Integer, middle: String = null) {
  private val this.first = first
  private val this.last = last
  private val this.born = born
  private val this.middle = middle

  def getFirst() = this.first
  def getLast() = this.last
  def getBorn() = this.born
  def getMiddle() = this.middle
}
