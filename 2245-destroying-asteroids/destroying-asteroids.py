class Solution:
    def asteroidsDestroyed(self, mass: int, asteroids: list[int]) -> bool:
        # Sort asteroids from smallest to largest
        asteroids.sort()
        
        for asteroid in asteroids:
            # If the current asteroid is too heavy, we fail
            if mass < asteroid:
                return False
            # Otherwise, absorb its mass and grow
            mass += asteroid
            
        return True
