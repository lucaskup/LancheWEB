  --###############################
  --#    Create Sequence          #
  --###############################
  

  DROP PROCEDURE IF EXISTS CreateSequence;
 
  DELIMITER //
 
  CREATE PROCEDURE CreateSequence (name VARCHAR(30), start INT, inc INT)
  BEGIN
     -- Create a table to store sequences
     CREATE TABLE IF NOT EXISTS _sequences
     (
         name VARCHAR(70) NOT NULL UNIQUE,
         next INT NOT NULL,
         inc INT NOT NULL
     );
 
     -- Add the new sequence
     INSERT INTO _sequences VALUES (name, start, inc);  
  END
  //
  DELIMITER ;
  
  --###############################
  --#     DROP SEQUENCE           #
  --###############################
  
   DROP PROCEDURE IF EXISTS DropSequence;
 
  DELIMITER //
 
  CREATE PROCEDURE DropSequence (vname VARCHAR(30))
  BEGIN
     -- Drop the sequence
     DELETE FROM _sequences WHERE name = vname;  
  END
  //
  DELIMITER ;
  
  
  --###############################
  --#            NEXTVAL          #
  --###############################
  
   DROP FUNCTION IF EXISTS NextVal;
 
  DELIMITER //
 
  CREATE FUNCTION NextVal (vname VARCHAR(30))
    RETURNS INT
  BEGIN
     -- Retrieve and update in single statement
     UPDATE _sequences
       SET next = (@next := next) + inc
       WHERE name = vname;
 
     RETURN @next;
  END
  //
  DELIMITER ;
  
  -- ####################################
  -- #         Cria a Sequence          #
  -- ####################################
  
  CALL `lanche`.`CreateSequence`('seq_numPedido',1,1);

  