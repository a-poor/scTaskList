// package scTaskList

import concurrent.Future
import java.lang.Thread.sleep
import concurrent.ExecutionContext.Implicits.global
import concurrent.duration.{Duration, MILLISECONDS}


class TaskList(tasks: Seq[Task], updateEvery: Int = 100):
  private var si = Map[TaskStatus, StatusIndicator](
    TaskStatus.NotStarted -> StatusIndicator.newIndicatorNotStarted(),
    TaskStatus.Running    -> StatusIndicator.newIndicatorRunning(),
    TaskStatus.Skipped    -> StatusIndicator.newIndicatorSkipped(),
    TaskStatus.Succeeded  -> StatusIndicator.newIndicatorSucceeded(),
    TaskStatus.Failed     -> StatusIndicator.newIndicatorNotStarted(),
  )

  private def getStatuses(): Map[TaskStatus, Char] = Map[TaskStatus, Char](
    TaskStatus.NotStarted -> si(TaskStatus.NotStarted).getThenInc(),
    TaskStatus.Running    -> si(TaskStatus.Running).getThenInc(),
    TaskStatus.Skipped    -> si(TaskStatus.Skipped).getThenInc(),
    TaskStatus.Succeeded  -> si(TaskStatus.Succeeded).getThenInc(),
    TaskStatus.Failed     -> si(TaskStatus.Failed).getThenInc(),
  )

  def run(parallel: Boolean = false): Unit = {
    printTasks()
    val res = Future {
      tasks.foreach(t => t.run())
    }

    while (!res.isCompleted) {
      clearTasks()
      printTasks()
      sleep(updateEvery)
    }
    clearTasks()
    printTasks()
  }

  private def printTasks(): Unit = {
    val statuses = getStatuses()
    tasks.foreach(t => {
      val s = statuses(t.getStatus())
      val n = t.getName()
      print(f"$s ${n}" + "\n")
    })
  }

  private def clearTasks(): Unit = tasks.foreach(_ => {
    print("\u001B[1A") // Move up a line
    print("\u001B[K")  // Clear the line
    print("\r")        // Move back to tbe beginning of the line
  })

object TaskList:
  def apply(tasks: Seq[Task], updateEvery: Int = 100) = new TaskList(tasks, updateEvery)

