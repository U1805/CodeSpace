<template>
    <div class="full-container">
        <CodeLayout ref="codeLayout" :layout-config="config">
            <template #centerArea>
                <h1>Center Area</h1>
            </template>
            <template #titleBarIcon>
                <img src="../assets/images/logo.svg" width="20px" style="margin:0 10px 0 13px">
            </template>
            <template #panelRender="{ panel }">
                <template v-if="panel.name === 'explorer.file'">
                    <h1>Render Panel Explorer File</h1>
                </template>
                <template v-else-if="panel.name === 'explorer.outline'">
                    <h1>Render Panel Explorer outline</h1>
                </template>
                <template v-else-if="panel.name === 'bottom.ports'">
                    <h1>Render bottom panel ports</h1>
                </template>
                <template v-else-if="panel.name === 'bottom.terminal'">
                    <h1>Render bottom panel terminal</h1>
                </template>
                <span v-else>Panel {{ panel.name }}, no content</span>
            </template>
            <template #statusBar>
                <span>Custom render Status bar area</span>
            </template>
        </CodeLayout>
    </div>
</template>