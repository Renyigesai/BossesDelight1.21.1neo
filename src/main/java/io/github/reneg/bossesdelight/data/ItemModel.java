package io.github.reneg.bossesdelight.data;


import io.github.reneg.BossesDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;
import java.util.function.Supplier;

public class ItemModel extends ItemModelProvider {

    public ItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BossesDelight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        Class<BakeriesItems> _class = BakeriesItems.class;
//        for (Field field : _class.getDeclaredFields()) {
//            boolean isAnnotationPresent = field.isAnnotationPresent(ItemData.class);
//            if (isAnnotationPresent){
//                try {
//                    Object object = field.get(null);
//                    if (object instanceof DeferredItem<?> deferredItem){
//                        ItemData annotation = field.getAnnotation(ItemData.class);
//                        ItemData.ModelType model = annotation.model();
//                        if (model != ItemData.ModelType.CUSTOM) {
//                            Item item = deferredItem.get();
//                            if (model == ItemData.ModelType.GENERAL) {
//                                basicItem(item);
//                            }
//                            if (model == ItemData.ModelType.TOOL) {
//                                toolItem(item);
//                            }
//                            if (isBlockItem(item)){
//                                BlockItem blockItem = (BlockItem) item;
//                                if (model == ItemData.ModelType.BLOCK){
//                                    blockItem(blockItem::getBlock);
//                                }
//                                if (model == ItemData.ModelType.BREAD){
//                                    blockItem(blockItem::getBlock,"_1");
//                                }
//                            }
//                        }
//                    }
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
    }

    private boolean isBlockItem(Item item){
        return item instanceof BlockItem;
    }

    private ItemModelBuilder rawBreadItem(Supplier<Item> bread, Supplier<Block> block, String index) {
        return this.getBuilder(this.name(bread.get()))
                .parent(new ModelFile.UncheckedModelFile(this.modLoc("block/" + this.name(block.get())+index)))
                .texture("0", this.modLoc("item/" + this.name(bread.get())));
    }

    private ItemModelBuilder customModelItem(Supplier<Item> item, String path) {
        return this.getBuilder(this.name(item.get())).parent(new ModelFile.UncheckedModelFile(this.modLoc(path)));
    }

    private void usingItem(Item item) {
        this.toolItem((ResourceLocation) Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)),1);
        usingItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }
    private ItemModelBuilder usingItem(ResourceLocation item) {
        return getBuilder(item.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "item/" + item.getPath()))
                .override()
                .predicate(this.modLoc("using"), 0.0f)
                .model(this.generated(item,0)).end()
                .override()
                .predicate(this.modLoc("using"), 1.0f)
                .model(this.generated(item,1))
                .end();
    }
    private ItemModelBuilder createToolItem(ResourceLocation item,String name) {
        return getBuilder(item.toString()+name)
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "item/" + item.getPath()));
    }

    public ItemModelBuilder toolItem(Item item) {
        return createToolItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)),"");
    }

    public ItemModelBuilder toolItem(ResourceLocation item) {
        return createToolItem(item,"");
    }
    private ItemModelBuilder createBlockItem(Block block, String texturePath) {
        return this.withExistingParent(this.name(block), this.mcLoc("item/generated"))
                .texture("layer0", texturePath != null ? this.modLoc("item/" + texturePath) : this.modLoc("item/" + this.name(block)));
    }

    public ItemModelBuilder blockItem(Block block, String recource) {
        return createBlockItem(block, recource);
    }

    public ItemModelBuilder blockItem(Block block) {
        return createBlockItem(block, null);
    }
    private ItemModelBuilder simpleBlockItem(Supplier<Item> item) {
        return getBuilder(this.name(item.get()))
                .parent(new ModelFile.UncheckedModelFile(this.modLoc("block/" + this.name(item.get()))));
    }
    private ItemModelBuilder blockItem(Supplier<Block> block, String index) {
        return this.getBuilder(this.name(block.get()))
                .parent(new ModelFile.UncheckedModelFile(this.modLoc("block/" + this.name(block.get())+index)));
    }
    private void blockItem(Supplier<Block> block) {
        this.withExistingParent(this.name(block.get()), this.modLoc("block/" + this.name(block.get())));
    }
    private String name(Block block) {
        return Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block)).getPath();
    }
    private String name(Item item) {
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath();
    }
    public ModelFile generated(Item item, int index){
        return this.generated((ResourceLocation)Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), index);
    }
    public void basicItem_Stack_Size(Item item) {
        this.basicItem((ResourceLocation)Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)),1);
        this.basicItem((ResourceLocation)Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)),2);
        this.basicItem_Stack_Size((ResourceLocation)Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }
    public void basicItem_Stack_Size(ResourceLocation item) {
        this.getBuilder(item.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "item/" + item.getPath()))
                .override()
                .predicate(this.modLoc("stack_size"), 0.0F)
                .model(this.generated(item, 0)).end()
                .override()
                .predicate(this.modLoc("stack_size"), 1.0F)
                .model(this.generated(item, 1)).end()
                .override()
                .predicate(this.modLoc("stack_size"), 2.0F)
                .model(this.generated(item, 2))
                .end();
    }
    public ModelFile generated(ResourceLocation item, int index){
        if(index == 0){
            return this.getExistingFile(this.modLoc(item.getPath()));
        }
        return this.getExistingFile(this.modLoc(item.getPath()+"_"+index));
    }
    public ItemModelBuilder basicItem(Item item, int index) {
        return this.basicItem((ResourceLocation)Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), index);
    }
    public ItemModelBuilder basicItem(ResourceLocation item,int index) {
        return this.getBuilder(item.getPath() +"_"+index)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(BossesDelight.MODID, "item/" + item.getPath()+"_"+index)
                        );
    }
    public ItemModelBuilder toolItem(ResourceLocation item,int index) {
        return this.getBuilder(item.getPath() +"_"+index)
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0",
                        ResourceLocation.fromNamespaceAndPath(BossesDelight.MODID, "item/" + item.getPath()+"_"+index)
                );
    }
    public ResourceLocation blockLoc(Block block ,String name) {
        ResourceLocation  blockLoc = BuiltInRegistries.BLOCK.getKey(block);
        return ResourceLocation.fromNamespaceAndPath(blockLoc.getNamespace(), name);
    }

}