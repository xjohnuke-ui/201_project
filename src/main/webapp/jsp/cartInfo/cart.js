document.addEventListener('DOMContentLoaded', function() {
    // 获取所有数量按钮和输入框
    const cartItems = document.querySelectorAll('.cart-item');
    const totalPriceElement = document.querySelector('.total-price em');
    const selectedCountElement = document.querySelector('.total-info em');
    const selectAllCheckboxes = document.querySelectorAll('.select-all');

    // 为每个商品添加事件监听
    cartItems.forEach(item => {
        const minusBtn = item.querySelector('.minus');
        const plusBtn = item.querySelector('.plus');
        const quantityInput = item.querySelector('.quantity-input');
        const checkbox = item.querySelector('input[type="checkbox"]');
        const priceElement = item.querySelector('.item-price');
        const totalElement = item.querySelector('.item-total');

        // 获取单价（去掉¥符号并转换为数字）
        const price = parseFloat(priceElement.textContent.replace('¥', ''));

        // 更新小计
        function updateItemTotal() {
            const quantity = parseInt(quantityInput.value);
            const total = (price * quantity).toFixed(2);
            totalElement.textContent = `¥${total}`;
            updateCartTotal();
        }

        // 减少数量
        minusBtn.addEventListener('click', () => {
            let quantity = parseInt(quantityInput.value);
            if (quantity > 1) {
                quantityInput.value = quantity - 1;
                updateItemTotal();
            }
        });

        // 增加数量
        plusBtn.addEventListener('click', () => {
            let quantity = parseInt(quantityInput.value);
            quantityInput.value = quantity + 1;
            updateItemTotal();
        });

        // 手动输入数量
        quantityInput.addEventListener('change', () => {
            let quantity = parseInt(quantityInput.value);
            if (isNaN(quantity) || quantity < 1) {
                quantityInput.value = 1;
            }
            updateItemTotal();
        });

        // 复选框变化
        checkbox.addEventListener('change', () => {
            updateCartTotal();
        });
    });

    // 全选功能
    selectAllCheckboxes.forEach(selectAll => {
        selectAll.addEventListener('change', () => {
            const isChecked = selectAll.checked;
            // 同步所有全选框的状态
            selectAllCheckboxes.forEach(checkbox => {
                checkbox.checked = isChecked;
            });
            // 更新所有商品的选中状态
            cartItems.forEach(item => {
                item.querySelector('input[type="checkbox"]').checked = isChecked;
            });
            updateCartTotal();
        });
    });

    // 更新购物车总计
    function updateCartTotal() {
        let totalPrice = 0;
        let selectedCount = 0;

        cartItems.forEach(item => {
            const checkbox = item.querySelector('input[type="checkbox"]');
            if (checkbox.checked) {
                const total = parseFloat(item.querySelector('.item-total').textContent.replace('¥', ''));
                totalPrice += total;
                selectedCount++;
            }
        });

        // 更新总价和选中数量
        totalPriceElement.textContent = `¥${totalPrice.toFixed(2)}`;
        selectedCountElement.textContent = selectedCount;

        // 更新全选框状态
        const allChecked = selectedCount === cartItems.length;
        selectAllCheckboxes.forEach(checkbox => {
            checkbox.checked = allChecked;
        });
    }
});