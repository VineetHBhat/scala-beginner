package lectures.part2oop

object Enums {
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // We can add fields and/or methods
    def openDocument(): Unit =
      if (this == READ) println("Opening document...")
      else println("Reading not allowed !")
  }
  // The only possible instances of this type (Permissions) is READ, WRITE, EXECUTE, NONE

  val somePermissions: Permissions = Permissions.READ

  // Constructor arguments
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // we can also have companion objects for enums
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // whatever here
      PermissionsWithBits.NONE
  }

  // Standard API of enums that comes built-in in to enums
  val somePermissionsOrdinal  = somePermissions.ordinal
  val allPermissions = PermissionsWithBits.values // array of all possible values of the enum
  val readPermission: Permissions = Permissions.valueOf("READ") // can only be accessed on enums with no constructor arguments
  // valueOf parses the string and matches it to a value in the enum
  // for readPermission, valueOf will return Permissions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
  }
}
