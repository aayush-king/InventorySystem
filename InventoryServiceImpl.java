import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryTransactionRepository transactionRepository;

    @Override
    @Transactional
    public void deductInventory(Long productId, int quantity) throws InventoryException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new InventoryException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new InventoryException("Insufficient inventory");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setProductId(productId);
        transaction.setQuantity(quantity);
        transaction.setType(TransactionType.DEDUCTION);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void addInventory(Long productId, int quantity) throws InventoryException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new InventoryException("Product not found"));

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setProductId(productId);
        transaction.setQuantity(quantity);
        transaction.setType(TransactionType.ADDITION);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    public List<InventoryTransaction> getTransactionsByProductId(Long productId) {
        return transactionRepository.findByProductId(productId);
    }
}
