// package scTaskList

class Task(var name: String, action: () => Unit): // extends TaskRunner:
  private var status = TaskStatus.NotStarted
  private var statusIndicator = StatusIndicator.fromStatus(status)
  private var error: Throwable = null

  def getName(): String = name

  def setName(n: String): Unit = {
    name = n
  }

  def getStatus(): TaskStatus = status

  def setStatus(s: TaskStatus): Unit = {
    status = s
    statusIndicator = StatusIndicator.fromStatus(status)
  }

  def getError(): Throwable = error

  def setError(e: Throwable): Unit = {
    error = e
  }

  def run(): Unit = {
    setStatus(TaskStatus.Running)
    try {
      action()
      setStatus(TaskStatus.Succeeded)
    } catch {
      case e: Throwable => {
        setStatus(TaskStatus.Failed)
        setError(e)
      }
    }
  }

  def printTask(indent: Int = 0): Unit = {
    println(" " * indent + statusIndicator.getThenInc() + " " + name)
  }
  def clearTask(): Unit = {
    print("\u001B[1A") // Move up a line
    print("\u001B[K")  // Clear the line
    print("\r")        // Move back to tbe beginning of the line
  }

object Task

