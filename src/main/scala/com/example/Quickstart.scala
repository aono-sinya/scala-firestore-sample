package com.example

import com.google.cloud.firestore.FirestoreOptions

class Quickstart {
  private val db = FirestoreOptions
    .newBuilder()
    .setHost("localhost:8080")
    .build()
    .getService()

  def getDb() = db

  def addDocument(docName: String): Unit = {
    val result = docName match {
      case "alovelace" =>
        val docRef = db.collection("users").document("alovelace")
        val data = new User("Ada", "Lovelace", 1815)
        docRef.set(data)
      case "aturing" =>
        val docRef = db.collection("users").document("aturing")
        val data = new User("Alan", "Turing", 1912, "Mathison")
        docRef.set(data)
      case "cbabbage" =>
        val docRef = db.collection("users").document("cbabbage")
        val data = new User("Charles", "Babbage", 1791)
        docRef.set(data)
    }
    println("Update time : " + result.get().getUpdateTime())
  }

  def runAQuery(): Unit = {
    val query = db.collection("users").whereLessThan("born", 1900).get()
    val querySnapshot = query.get()
    val documents = querySnapshot.getDocuments()
    documents.forEach{document =>
      println("User: " + document.getId())
      println("First: " + document.getString("first"))
      if (document.getString("middle") != null) println("Middle: " + document.getString("middle"))
      println("Last: " + document.getString("last"))
      println("Born: " + document.getLong("born"))
    }
  }

  def retrieveAllDocuments(): Unit = {
    val query = db.collection("users").get()
    val querySnapshot = query.get()
    val documents = querySnapshot.getDocuments()
    documents.forEach{document =>
      println("User: " + document.getId())
      println("First: " + document.getString("first"))
      if (document.getString("middle") != null) println("Middle: " + document.getString("middle"))
      println("Last: " + document.getString("last"))
      println("Born: " + document.getLong("born"))
    }
  }

  def run(): Unit = {
    val docNames = Array("alovelace", "aturing", "cbabbage")
    println("########## Adding document 1 ##########")
    addDocument(docNames(0))
    println("########## Adding document 2 ##########")
    addDocument(docNames(1))
    println("########## Adding document 3 ##########")
    addDocument(docNames(2))
    println("########## users born before 1900 ##########")
    runAQuery()
    println("########## All users ##########")
    retrieveAllDocuments()
    println("###################################")
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val quickStart = new Quickstart()
    quickStart.run()
  }
}