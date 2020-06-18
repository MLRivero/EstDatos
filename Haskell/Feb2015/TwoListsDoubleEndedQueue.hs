-------------------------------------------------------------------------------
-- Estructuras de Datos. Grado en Informática, IS e IC. UMA.
-- Examen de Febrero 2015.
--
-- Implementación del TAD Deque
--
-- Apellidos:
-- Nombre:
-- Grado en Ingeniería ...
-- Grupo:
-- Número de PC:
-------------------------------------------------------------------------------

module TwoListsDoubleEndedQueue
   ( DEQue
   , empty
   , isEmpty
   , first
   , last
   , addFirst
   , addLast
   , deleteFirst
   , deleteLast
   ) where

import Prelude hiding (last)
import Data.List(intercalate)
import Test.QuickCheck

data DEQue a = DEQ [a] [a]

-- Complexity:
empty :: DEQue a
empty = DEQ [] []

-- Complexity:
isEmpty :: DEQue a -> Bool
isEmpty (DEQ [] []) = True
isEmpty _ = False


-- Complexity:
addFirst :: a -> DEQue a -> DEQue a
addFirst x (DEQ [] []) = DEQ (x:[]) []
addFirst x (DEQ xs []) = DEQ (x:xs) []
addFirst x (DEQ [] ys) = DEQ (x:[]) ys
addFirst x (DEQ xs ys) = DEQ (x:xs) ys

-- Complexity:
addLast :: a -> DEQue a -> DEQue a
addLast y (DEQ [] []) = DEQ [] (y:[])
addLast y (DEQ xs []) = DEQ xs (y:[])
addLast y (DEQ [] ys) = DEQ [] (y:ys)
addLast y (DEQ xs ys) = DEQ xs (y:ys)

-- Complexity:
first :: DEQue a -> a
first (DEQ [] []) = error "Lista vacia"
first (DEQ [] ys) = head (reverse ys)
--first (DEQ (x:xs) ys) = x
first (DEQ xs ys) = head (xs)

-- Complexity:
last :: DEQue a -> a
last (DEQ [] []) = error "Lista vacia"
last (DEQ xs []) = head (reverse xs)
last (DEQ xs ys) = head ys

-- Complexity:
deleteFirst :: DEQue a -> DEQue a
deleteFirst (DEQ [] []) = error "Lista vacia"
deleteFirst (DEQ (x:xs) ys) = DEQ xs ys
deleteFirst (DEQ [] ys)
 | even (length ys) = DEQ (reverse (drop ((length ys) `div` 2) ys)) (take ((length ys) `div` 2) ys)
 | otherwise = DEQ (reverse (drop ((length ys) `div` 2) ys)) (take (((length ys) `div` 2) + 1) ys)


-- Complexity:
deleteLast :: DEQue a -> DEQue a
deleteLast (DEQ xs (y:ys)) = DEQ xs ys
deleteLast (DEQ xs [])
 | even (length xs) = DEQ (take ((length xs) `div` 2) xs) (reverse (drop ((length xs) `div` 2) xs))
 | otherwise = DEQ (take ((length xs) `div` 2) xs) (reverse (drop (((length xs) `div` 2) + 1) xs))



instance (Show a) => Show (DEQue a) where
   show q = "TwoListsDoubleEndedQueue(" ++ intercalate "," [show x | x <- toList q] ++ ")"

toList :: DEQue a -> [a]
toList (DEQ xs ys) =  xs ++ reverse ys

instance (Eq a) => Eq (DEQue a) where
   q == q' =  toList q == toList q'

instance (Arbitrary a) => Arbitrary (DEQue a) where
   arbitrary =  do
      xs <- listOf arbitrary
      ops <- listOf (oneof [return addFirst, return addLast])
      return (foldr id empty (zipWith ($) ops xs))
