from collections import defaultdict, Counter

class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        
    def find(self, i):
        if self.parent[i] == i:
            return i
        self.parent[i] = self.find(self.parent[i]) # Path compression
        return self.parent[i]
        
    def union(self, i, j):
        root_i = self.find(i)
        root_j = self.find(j)
        if root_i != root_j:
            self.parent[root_i] = root_j

class Solution:
    def minimumHammingDistance(self, source: list[int], target: list[int], allowedSwaps: list[list[int]]) -> int:
        n = len(source)
        uf = UnionFind(n)
        
        # 1. Connect indices based on allowed swaps
        for u, v in allowedSwaps:
            uf.union(u, v)
            
        # 2. Build the "supply bags" for each connected component
        # Dictionary mapping: root_index -> Counter of source values
        component_supply = defaultdict(Counter)
        for i in range(n):
            root = uf.find(i)
            component_supply[root][source[i]] += 1
            
        hamming_distance = 0
        
        # 3. Try to satisfy the target array from our supply bags
        for i in range(n):
            root = uf.find(i)
            needed_val = target[i]
            
            # If we have the required value in this component's bag, use it
            if component_supply[root][needed_val] > 0:
                component_supply[root][needed_val] -= 1
            else:
                # We couldn't find a match, distance increases
                hamming_distance += 1
                
        return hamming_distance
