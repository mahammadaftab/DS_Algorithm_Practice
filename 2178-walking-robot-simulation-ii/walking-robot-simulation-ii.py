class Robot:
    def __init__(self, width: int, height: int):
        self.w = width
        self.h = height
        # Total cells in the perimeter loop
        self.perimeter = 2 * (width - 1) + 2 * (height - 1)
        # 1D position tracking
        self.pos = 0
        self.moved = False

    def step(self, num: int) -> None:
        # Modulo optimization to instantly find the resting position
        self.pos = (self.pos + num) % self.perimeter
        self.moved = True

    def getPos(self) -> list[int]:
        # Bottom Edge (Moving East)
        if self.pos < self.w:
            return [self.pos, 0]
        # Right Edge (Moving North)
        elif self.pos < self.w + self.h - 1:
            return [self.w - 1, self.pos - (self.w - 1)]
        # Top Edge (Moving West)
        elif self.pos < 2 * self.w + self.h - 2:
            return [self.w - 1 - (self.pos - (self.w + self.h - 2)), self.h - 1]
        # Left Edge (Moving South)
        else:
            return [0, self.h - 1 - (self.pos - (2 * self.w + self.h - 3))]

    def getDir(self) -> str:
        # The Origin Trap
        if self.pos == 0:
            return "South" if self.moved else "East"
            
        # Direction maps perfectly to which edge the robot is currently on
        if self.pos < self.w:
            return "East"
        elif self.pos < self.w + self.h - 1:
            return "North"
        elif self.pos < 2 * self.w + self.h - 2:
            return "West"
        else:
            return "South"