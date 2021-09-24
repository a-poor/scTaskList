
class StatusIndicator(chars: Seq[Char]):
  var index = 0

  def get(): Char = chars(index)

  def inc(by: Int = 1): Unit = {
    index = math.abs(index + by) % chars.length
  }

  def getThenInc(by: Int = 1): Char = {
    val n = get()
    inc(by)
    n
  }

object StatusIndicator:

  val charsNotStarted = Seq('➜')
  val charsRunning = Seq('⣾','⣽','⣻','⢿','⡿','⣟','⣯','⣷')
  val charsSkipped = Seq('↓')
  val charsSucceeded = Seq('✓')
  val charsFailed = Seq('✗')

  def apply(chars: Seq[Char]) = new StatusIndicator(chars)

  def newIndicatorNotStarted() = this(charsNotStarted)
  def newIndicatorRunning() = this(charsRunning)
  def newIndicatorSkipped() = this(charsSkipped)
  def newIndicatorSucceeded() = this(charsSucceeded)
  def newIndicatorFailed() = this(charsFailed)

  def fromStatus(s: TaskStatus) = s match {
    case TaskStatus.NotStarted => newIndicatorNotStarted()
    case TaskStatus.Running => newIndicatorRunning()
    case TaskStatus.Skipped => newIndicatorSkipped()
    case TaskStatus.Succeeded => newIndicatorSucceeded()
    case TaskStatus.Failed => newIndicatorFailed()
  }
