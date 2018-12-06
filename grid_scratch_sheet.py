#usr/bin/env python3

# Function takes n, the length of a row.
# Grid is n * n.
# Compute col and row coordinates from a given site's site number (or index).

# Also, convert coordinates back to the site number.


class Grid(object):
	def __init__(self, n):
		self.n = n

	def coords_to_site(self, row, col):
		sitenum = self.n * row + col - self.n
		return sitenum

	def site_to_coords(self, sitenum):
		row = (sitenum - 1) // self.n + 1
		col = (sitenum - 1) % self.n + 1
		return row, col

	def right_site(self, row, col):
		if col < self.n:
			return self.coords_to_site(row, col) + 1

	def left_site(self, row, col):
		if col > 1:
			return self.coords_to_site(row, col) - 1

	def top_site(self, row, col):
		if row > 1:
			return self.coords_to_site(row, col) - self.n

	def bottom_site(self, row, col):
		if row < self.n:
			return self.coords_to_site(row, col) + self.n

mygrid = Grid(5)
print("site:    {}".format(mygrid.coords_to_site(4, 2)))
print("coords:  {}".format(mygrid.site_to_coords(17)))
print("right:   {}".format(mygrid.right_site(4, 2)))
print("left:    {}".format(mygrid.left_site(4, 2)))
print("top:     {}".format(mygrid.top_site(4, 2)))
print("bottom: {}".format(mygrid.bottom_site(4, 2)))



# parent[1] = 1		1  2  3  4  5
# parent array		1  2  3  4  5

# union(1, 3)

# parent[1] = 3		3  2  3  4  5	value
# parent array		1  2  3  4  5	index


# Index to coords:
# index 0 is (1, 1)
# index 5 is (2, 1)
# index 17 is (4, 3)
# index 14 is (3, 5)
# index 24 is (5, 5)
# i // side + 1 = row
# i % side + 1 = col 




# for (int = n * (n - 1); x < n; x++)
# or...
# private boolean percolates = False
# From inside 
# if 

	# sitenum = n * row + col - n
	# sitenum + n = n * row + col
	# sitenum + n - col = n * row
	# (sitenum + n - col) / n = row

# Sitenum to coords:
	# sitenum = n * row + col - n
	# sitenum + n = col + n * row
	# sitenum + n - n * row = col
		# Problem: col or row referenced before assignment.

	# 25 / 5 = 5 (col)
	# 25 // 5 = 5 (row)

	# 24 + 5 % 5 = 5 (row)
	# 24 % 5 = 4 (col)

	# 11 % 5 = 1 (col)
	# 11 // 5 + 1 = 3 (row)


	# 6 = (2, 1)
	# 7 = (2, 2)
	# 8 = (2, 3)
	# 9 = (2, 4)
	# 10 = (2, 5)

	# 4 = (1, 4)
	# 9 = (2, 4)
	# 14 = (3, 4)
	# 19 = (4, 4)
	# 24 = (5, 4)

	# (sitenum - 1) // 5 + 1 = row
	# (1 - 1) // 5 + 1 = 1
	# (5 - 1) // 5 + 1 = 1
	# (6 - 1) // 5 + 1 = 2
	# (10 - 1) // 5 + 1 = 2
	# (25 - 1) // 5 + 1 = 5

	# (sitenum - 1) % 5 + 1 = col
	# (6 - 1) % 5 + 1 = 1
	# (7 - 1) % 5 + 1 = 2

	# (23 - 1) % 5 + 1 = 3
	# (25 - 1) % 5 + 1 = 5