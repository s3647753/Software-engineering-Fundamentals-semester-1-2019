Dev Notes for Chess-Like-Game

TODO
	think about extra marks
		do the warn player that a move could result in the loss of the piece
	

version
	v1.0
		Basic high level class view.
		Starting to flesh out core methods.
		
----------------
Piece, Cell, Board, move and split rational
	Point
		Point is about encapsulation of the coordinates of a cells location on the board
		Additionally Point is used as the type in the translation vectors that the Pieces contain
		Adding the two points together returns a new Point containing the vector sum of the two points.

    Piece
        knows what it is. (type, color)
        does not know where it is
        
    Cell
        Cell's reason to exist is to allow merging of two pieces into one board location
        can hold one or two Pieces (in an ArrayList)
        an empty List indicates that the cell is empty
        
    Board
        has a 2D array of Cells
        The array structure allows direct indexing of a location
        Where there is nothing in the board an empty cell will be in the array location
        The board is initialised with empty cells then the starting game pieces can be put into the cells
        
    moving
        when a piece is moved, the Piece/s is/are taken from the cell by the board
            and put into the destination cell
        The reason for not moving cells is to make merge/split easy <------
        
        if there is an opponents piece in the cell it is deleted, and the score adjusted by GE
        if there is a players piece in the cell then the two pieces are merged automatically
        
        when the piece being moved is a merged piece
        the player should be asked is they wish to split,
              the piece that can move is moved and the remaining piece remains in the cell's list
    
    Finding potential moves
        Piece
            Piece contains a set of vectors using the Point subclasses
            each of the subclasses vectors should be a 'private static final' constant (no setter)
            these vectors represent the set of potential moves the piece might make
            they may not all be valid but Piece does not care.
            (Board will decide which are valid and which are not)
            the piece will return this set of point vectors when asked for potential moves
            
        Cell
            when asked for potential moves
                if the cell contains one Piece then it should return exactly what the piece returns
                if the cell contains two pieces, the two potentialMoves vectors are concatenated and returned
                
        Board
            board does not provide potential moves, it provides legal moves <------
            board has the logic to decide if a move is legal and shall return a list of
            legal Point vectors when getLegalMoves(Point point) is called
            
        GameEngine (GE)
            GE shall provide all of the logic for moving and scoring except for the
                determination of legal moves which the board will determine
        
    Summary of Merging/moving rules
        if the destination cell is empty the piece is moved from the source cell to the destination cell
        If an opponent piece is in the destination is shall be deleted, the GE will adjust the score and the piece moved
        If another of the players pieces is in the cell then:
            if it is of the same type the move is illegal <-----**
            if the piece is of a different type then the two pieces are merged into the destination cell automatically
        When moving a merged cell the player will be asked if they wish to move the piece
-----------------		
		

			
				
				
				
				
				
		