public interface InventoryService {
    void deductInventory(Long productId, int quantity) throws InventoryException;

    void addInventory(Long productId, int quantity) throws InventoryException;

    List<InventoryTransaction> getTransactionsByProductId(Long productId);
}
