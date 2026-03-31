package com.example.help_desk.repository;

import com.example.help_desk.dto.CategoryTreeDTO;
import com.example.help_desk.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = """
        WITH CategoryTree AS (
            SELECT 
                Id, Name, parent_category_id, 0 AS CategoryLevel, 
                CAST(Name AS NVARCHAR(MAX)) AS Path
            FROM Categories
            WHERE parent_category_id IS NULL
            
            UNION ALL
            
            SELECT 
                C.Id, C.Name, C.parent_category_id, CT.CategoryLevel + 1, 
                CAST(CT.Path + ' -> ' + C.Name AS NVARCHAR(MAX))
            FROM Categories C
            INNER JOIN CategoryTree CT ON C.parent_category_id = CT.Id
        )
        SELECT 
            T.Id AS id, 
            REPLICATE('---- ', T.CategoryLevel) + T.Name AS visualTree, 
            T.Path AS fullHierarchy,
            T.CategoryLevel AS categoryLevel,
            CAST(CASE WHEN EXISTS (SELECT 1 FROM Categories C WHERE C.parent_category_id = T.Id) THEN 0 ELSE 1 END AS BIT) AS isLeafNode
        FROM CategoryTree T
        ORDER BY T.Path;
        """, nativeQuery = true)
    List<CategoryTreeDTO> getCategoryTree();
}